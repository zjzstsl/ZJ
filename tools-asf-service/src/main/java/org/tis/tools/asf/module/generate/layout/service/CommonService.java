package org.tis.tools.asf.module.generate.layout.service;

import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.generate.engine.metadata.java.JavaBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.extend.ExtendsBase;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/11
 **/
public class CommonService extends JavaBase {

    private static final String CLASS_NAME_PREFIX = "I";
    private static final String CLASS_NAME_SUFFIX = "Service";
    private static final String PACKAGE_SUFFIX = ".service";


    private CommonService() {}

    public static CommonService instance(String packageInfo, BizModel model) {
        CommonService s = new CommonService();
        // 设置package
        s.setPackageInfo(packageInfo + PACKAGE_SUFFIX);
        // 类注释
        s.setDoc(JavadocBase.createClassDoc(model.getPhysicalName() + "的Service接口类"));
        // 类声明
        s.setDeclaration("public");
        // 类型
        s.setType("interface");
        // 类名
        s.setName(className(model.getPhysicalName()));
        // 类继承
        s.setExtend(getExtends(packageInfo, model));
        return  s;
    }

    /**
     * 生成实体的Service类名： employee-> IEmployeeService
     * @param name
     * @return
     */
    private static String className(String name) {
        return CLASS_NAME_PREFIX + GenerateUtils.firstUpperCase(name) + CLASS_NAME_SUFFIX;
    }

    private static ExtendsBase getExtends(String packageInfo, BizModel bizModel) {
        String modelName = GenerateUtils.firstUpperCase(bizModel.getPhysicalName());
        String baseMapperImports = "com.baomidou.mybatisplus.service.IService";
        String modelImports = packageInfo + ".entity." + modelName;
        String extendName = "IService<%s>";
        extendName = String.format(extendName, modelName);
        return new ExtendsBase(extendName, baseMapperImports, modelImports);
    }
}
