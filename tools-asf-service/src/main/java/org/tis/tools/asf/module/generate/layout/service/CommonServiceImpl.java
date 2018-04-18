package org.tis.tools.asf.module.generate.layout.service;

import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.generate.engine.metadata.java.JavaBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationField;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.extend.ExtendsBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.implement.ImplementsBase;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/11
 **/
public class CommonServiceImpl extends JavaBase {

    private static final String CLASS_NAME_SUFFIX = "ServiceImpl";

    private static final String SERVICE_NAME_PREFIX = "I";

    private static final String SERVICE_NAME_SUFFIX = "Service";

    private static final String MAPPER_NAME_SUFFIX = "Mapper";

    private static final String PACKAGE_SUFFIX = ".service.impl";


    private CommonServiceImpl() {}

    public static CommonServiceImpl instance(String packageInfo, BizModel model) {
        CommonServiceImpl s = new CommonServiceImpl();
        // 设置package
        s.setPackageInfo(packageInfo + PACKAGE_SUFFIX);
        // 类注释
        s.setDoc(JavadocBase.createClassDoc(model.getPhysicalName() + "的Service接口实现类"));
        // 类注解
        s.setAnnotations(getClassAnnos());
        // 类声明
        s.setDeclaration("public");
        // 类型
        s.setType("class");
        // 类名
        s.setName(className(model.getPhysicalName()));
        // 类继承
        s.setExtend(getExtends(packageInfo, model));
        // 类实现
        s.setImpls(getImpls(packageInfo, model));
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

    private static List<AnnotationBase> getClassAnnos() {
        List<AnnotationBase> classAnnos = new ArrayList<>();
        classAnnos.add(new AnnotationBase("Service","org.springframework.stereotype.Service"));
        List<AnnotationField> annotationFields = Collections.singletonList(
                new AnnotationField("rollbackFor", "Exception.class", false));
        classAnnos.add(new  AnnotationBase("Transactional", annotationFields,
                "org.springframework.transaction.annotation.Transactional"));
        return classAnnos;
    }

    private static ExtendsBase getExtends(String packageInfo, BizModel bizModel) {
        String modelName = GenerateUtils.firstUpperCase(bizModel.getPhysicalName());
        String mapperName = GenerateUtils.firstUpperCase(bizModel.getPhysicalName() + MAPPER_NAME_SUFFIX);
        String baseMapperImports = "com.baomidou.mybatisplus.service.impl.ServiceImpl";
        String modelImports = packageInfo + ".entity." + modelName;
        String mapperImports = packageInfo + ".dao." + mapperName;
        String extendName = "ServiceImpl<%s, %s>";
        extendName = String.format(extendName, mapperName, modelName);
        return new ExtendsBase(extendName, baseMapperImports, modelImports, mapperImports);
    }

    private static List<ImplementsBase> getImpls(String packageInfo, BizModel bizModel) {
        String serviceName = SERVICE_NAME_PREFIX + GenerateUtils.firstUpperCase(bizModel.getPhysicalName()) + SERVICE_NAME_SUFFIX;
        String serviceImport = packageInfo + ".service." + serviceName;
        return Collections.singletonList(new ImplementsBase(serviceName, serviceImport));
    }
}
