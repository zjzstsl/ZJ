package org.tis.tools.asf.module.generate.layout.dao;

import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.generate.engine.metadata.java.JavaBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.extend.ExtendsBase;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/10
 **/
public class CommonMapper extends JavaBase {

    private static final String CLASS_NAME_SUFFIX = "Mapper";

    private static final String PACKAGE_SUFFIX = ".dao";

    private CommonMapper() {}

    public static CommonMapper instance(String packageInfo, BizModel model) {
        CommonMapper m = new CommonMapper();
        // 设置package
        m.setPackageInfo(packageInfo + PACKAGE_SUFFIX);
        // 类注释
        m.setDoc(JavadocBase.createClassDoc(model.getPhysicalName() + "的Mapper类"));
        // 类声明
        m.setDeclaration("public");
        // 类型
        m.setType("interface");
        // 类名
        m.setName(className(model.getPhysicalName()));
        // 类继承
        m.setExtend(getExtends(packageInfo, model));
        return  m;
    }

    /**
     * 生成实体的Mapper类名： employee-> EmployeeMapper
     * @param name
     * @return
     */
    private static String className(String name) {
        return GenerateUtils.firstUpperCase(name) + CLASS_NAME_SUFFIX;
    }

    private static ExtendsBase getExtends(String packageInfo, BizModel bizModel) {
        String modelName = GenerateUtils.firstUpperCase(bizModel.getPhysicalName());
        String baseMapperImports = "com.baomidou.mybatisplus.mapper.BaseMapper";
        String modelImports = packageInfo + ".entity." + modelName;
        String extendName = "BaseMapper<%s>";
        extendName = String.format(extendName, modelName);
        return new ExtendsBase(extendName, baseMapperImports, modelImports);
    }
}
