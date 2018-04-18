package org.tis.tools.asf.module.generate.layout.controller;

import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.generate.engine.metadata.java.JavaBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.CommonAnnotation;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.RestAnnotation;
import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.extend.ExtendsBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.field.FieldBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.method.MethodBase;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

import java.util.*;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/11
 **/
public class CommonController extends JavaBase {

    private static final String CLASS_NAME_SUFFIX = "Controller";
    private static final String SERVICE_NAME_PREFIX = "I";
    private static final String SERVICE_NAME_SUFFIX = "Service";

    private static final String PACKAGE_SUFFIX = ".controller";



    private CommonController() {}

    public static CommonController instance(String packageInfo, BizModel model) {
        CommonController s = new CommonController();
        // 设置package
        s.setPackageInfo(packageInfo + PACKAGE_SUFFIX);
        // 通用包
        s.getImports().addAll(addCommonImport());
        // 类注释
        s.setDoc(JavadocBase.createClassDoc(model.getPhysicalName() + "的Controller类"));
        // 类注解
        s.setAnnotations(getClassAnnos(model));
        // 类声明
        s.setDeclaration("public");
        // 类型
        s.setType("class");
        // 类名
        s.setName(className(model.getPhysicalName()));
        // 类继承
        s.setExtend(getExtends(packageInfo, model));
        // 类属性
        s.setFields(getField(packageInfo, model));
        // 类方法
        s.setMethods(getMethod(packageInfo, model));
        return  s;
    }

    /**
     * 生成实体的Service类名： employee-> IEmployeeService
     * @param name
     * @return
     */
    private static String className(String name) {
        return GenerateUtils.firstUpperCase(name) + CLASS_NAME_SUFFIX;
    }

    private static List<AnnotationBase> getClassAnnos(BizModel model) {
        List<AnnotationBase> classAnnos = new ArrayList<>();
        classAnnos.add(RestAnnotation.restController());
        classAnnos.add(RestAnnotation.requestMapping("/" + model.getPhysicalName()));
        return classAnnos;
    }

    private static ExtendsBase getExtends(String packageInfo, BizModel bizModel) {
        String modelName = GenerateUtils.firstUpperCase(bizModel.getPhysicalName());
        String baseExtendImports = "org.tis.tools.core.web.controller.BaseController";
        String modelImports = packageInfo + ".entity." + modelName;
        String extendName = "BaseController<%s>";
        extendName = String.format(extendName, modelName);
        return new ExtendsBase(extendName, baseExtendImports, modelImports);
    }

    private static List<FieldBase> getField(String packageInfo, BizModel bizModel) {
        FieldBase f = new FieldBase();
        f.setAnnotations(Collections.singletonList(CommonAnnotation.autowired()));
        f.setDeclaration("private");
        f.setType(SERVICE_NAME_PREFIX + GenerateUtils.firstUpperCase(bizModel.getPhysicalName()) + SERVICE_NAME_SUFFIX);
        f.setName(GenerateUtils.firstLowerCase(bizModel.getPhysicalName()) + SERVICE_NAME_SUFFIX);
        f.setImportClassName(new String[] {
                packageInfo + ".service." + f.getType()
        });
        return Collections.singletonList(f);
    }

    private static List<MethodBase> getMethod(String packageInfo, BizModel model) {
        String modelName = GenerateUtils.firstLowerCase(model.getPhysicalName());
        String serviceName = modelName + SERVICE_NAME_SUFFIX;
        List<MethodBase> methodBases = new ArrayList<>();
        methodBases.add(CommonRequestMethod.addMethod(serviceName, modelName));
        methodBases.add(CommonRequestMethod.updateMethod(serviceName, modelName));
        methodBases.add(CommonRequestMethod.deleteMapping(serviceName));
        methodBases.add(CommonRequestMethod.detailMapping(serviceName, modelName));
        methodBases.add(CommonRequestMethod.listMapping(serviceName, modelName));
        return methodBases;
    }

    private static Set<String> addCommonImport() {
        Set<String> imports = new HashSet<>();
        imports.add("org.tis.tools.core.web.vo.ResultVO");
        return imports;
    }

}
