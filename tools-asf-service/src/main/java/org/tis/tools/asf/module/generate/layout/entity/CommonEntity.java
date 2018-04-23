package org.tis.tools.asf.module.generate.layout.entity;

import org.apache.commons.lang.StringUtils;
import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.generate.engine.metadata.java.JavaBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.field.FieldBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.implement.ImplementsBase;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/10
 **/
public class CommonEntity extends JavaBase {

    private final static String PACKAGE_SUFFIX = ".entity";

    private CommonEntity() {}

    public static CommonEntity instance(String packageInfo, BizModel model) {
        CommonEntity entity = new CommonEntity();
        // 设置package
        entity.setPackageInfo(packageInfo + PACKAGE_SUFFIX);
        // 类注释
        entity.setDoc(JavadocBase.createClassDoc(model.getPhysicalName() +
                (StringUtils.isNotBlank(model.getDesc()) ? model.getDesc() : "")));
        // 类注解
        entity.setAnnotations(getClassAnnos(model.getPhysicalName()));
        // 类声明
        entity.setDeclaration("public");
        // 类型
        entity.setType("class");
        // 类名
        entity.setName(GenerateUtils.firstUpperCase(model.getPhysicalName()));
        // 类实现
        entity.setImpls(Collections.singletonList(
                new ImplementsBase("Serializable", "java.io.Serializable")));
        // 类属性 SerialVersionUID
        entity.setFields(getFields(model));
        return  entity;

    }

    /**
     * 类注解
     * @param modelName
     * @return
     */
    private static List<AnnotationBase> getClassAnnos(String modelName) {
        // 驼峰转下划线
        String tableName = GenerateUtils.camel2Underline(modelName).toLowerCase();
        List<AnnotationBase> classAnnos = new ArrayList<>();
        classAnnos.add(new AnnotationBase("Data","lombok.Data"));
        classAnnos.add(new  AnnotationBase("TableName", tableName,
                "com.baomidou.mybatisplus.annotations.TableName"));
        return classAnnos;
    }

    private static List<FieldBase> getFields(BizModel model) {
        List<FieldBase> fieldList = new ArrayList<>();
        fieldList.add(FieldBase.genSerialVersionUID());
        model.getFieldList().forEach(f -> fieldList.add(EntityFieldGenerator.columnField(f)));
        model.getFieldList().forEach(f -> fieldList.add(EntityFieldGenerator.generalField(f)));
        return fieldList;
    }




}
