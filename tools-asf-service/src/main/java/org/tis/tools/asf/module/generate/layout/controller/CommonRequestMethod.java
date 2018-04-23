package org.tis.tools.asf.module.generate.layout.controller;

import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.RestAnnotation;
import org.tis.tools.asf.module.generate.engine.metadata.java.method.MethodBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.method.MethodTemplate;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

import java.text.MessageFormat;
import java.util.Collections;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/11
 **/
public class CommonRequestMethod extends MethodBase {

    public static CommonRequestMethod addMethod(String service, String modelName) {
        String template = "public ResultVO add(@RequestBody @Validated {0} {2}) '{'\n" +
                "        {1}.insert({2});\n" +
                "        return ResultVO.success(\"新增成功！\");\n" +
                "    '}'";
        String[] params = {
                GenerateUtils.firstUpperCase(modelName),
                service,
                GenerateUtils.firstLowerCase(modelName)
        };
        String format = new MessageFormat(template).format(params);
        CommonRequestMethod m = new CommonRequestMethod();
        m.setAnnotations(Collections.singletonList(RestAnnotation.postMapping("/add")));
        m.setTemplate(new MethodTemplate(format, "org.springframework.validation.annotation.Validated",
                "org.springframework.validation.annotation.Validated"));
        return m;
    }

    public static CommonRequestMethod updateMethod(String service, String modelName) {
        String template = "public ResultVO update(@RequestBody @Validated {0} {2}) '{'\n" +
                "        {1}.updateById({2});\n" +
                "        return ResultVO.success(\"修改成功！\");\n" +
                "    '}'";
        String[] params = {
                GenerateUtils.firstUpperCase(modelName),
                service,
                GenerateUtils.firstLowerCase(modelName)
        };
        String format = new MessageFormat(template).format(params);
        CommonRequestMethod m = new CommonRequestMethod();
        m.setAnnotations(Collections.singletonList(RestAnnotation.putMapping()));
        m.setTemplate(new MethodTemplate(format, "org.springframework.validation.annotation.Validated",
                "org.springframework.validation.annotation.Validated"));
        return m;
    }

    public static CommonRequestMethod deleteMapping(String service) {
        String template = "public ResultVO delete(@PathVariable @NotBlank(message = \"id不能为空\") String id) '{'\n" +
                "        {0}.deleteById(id);\n" +
                "        return ResultVO.success(\"删除成功\");\n" +
                "    '}'";
        String[] params = {service};
        String format = new MessageFormat(template).format(params);
        CommonRequestMethod m = new CommonRequestMethod();
        m.setAnnotations(Collections.singletonList(RestAnnotation.deleteMapping("/{id}")));
        m.setTemplate(new MethodTemplate(format, "org.springframework.web.bind.annotation.*",
                "org.hibernate.validator.constraints.NotBlank"));
        return m;
    }

    public static CommonRequestMethod detailMapping(String service, String modelName) {
        String template = "public ResultVO detail(@PathVariable @NotBlank(message = \"id不能为空\") String id) '{'\n" +
                "        {0} {2} = {1}.selectById(id);\n" +
                "        if ({1} == null) '{'\n" +
                "            return ResultVO.error(\"404\", \"找不到对应记录或已经被删除！\");\n" +
                "        '}'\n" +
                "        return ResultVO.success(\"查询成功\", {2});\n" +
                "    '}'";
        String[] params = {GenerateUtils.firstUpperCase(modelName), service, GenerateUtils.firstLowerCase(modelName)};
        String format = new MessageFormat(template).format(params);
        CommonRequestMethod m = new CommonRequestMethod();
        m.setAnnotations(Collections.singletonList(RestAnnotation.getMapping("/{id}")));
        m.setTemplate(new MethodTemplate(format, "org.springframework.web.bind.annotation.*",
                "org.hibernate.validator.constraints.NotBlank"));
        return m;
    }

    public static CommonRequestMethod listMapping(String service, String modelName) {
        String template = "public ResultVO list(@RequestBody @Validated SmartPage<{0}> page) '{'\n" +
                "        return  ResultVO.success(\"查询成功\", {1}.selectPage(getPage(page), getCondition(page)));\n" +
                "    '}'";
        String[] params = {GenerateUtils.firstUpperCase(modelName), service, GenerateUtils.firstLowerCase(modelName)};
        String format = new MessageFormat(template).format(params);
        CommonRequestMethod m = new CommonRequestMethod();
        m.setAnnotations(Collections.singletonList(RestAnnotation.postMapping("/list")));
        m.setTemplate(new MethodTemplate(format,
                "org.springframework.web.bind.annotation.*",
                "org.tis.tools.core.web.vo.SmartPage"));
        return m;
    }



}
