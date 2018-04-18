package org.tis.tools.asf.module.generate.layout.dao;

import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

import java.text.MessageFormat;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/13
 **/
public class CommonMapperXml {

    private static final String MAPPER_XML_SUFFIX = "Mapper";

    private final static String PACKAGE_SUFFIX = ".dao.mapping";

    private String content;

    private String name;

    private String packageInfo;

    private CommonMapperXml() {}

    public static CommonMapperXml instance(String packageInfo, BizModel model) {
        CommonMapperXml s = new CommonMapperXml();
        String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\"{0}\">\n\n" +
                "</mapper>";
        String[] params = {
                packageInfo + ".dao." + GenerateUtils.firstUpperCase(model.getPhysicalName()) + "Mapper"
        };
        String format = new MessageFormat(template).format(params);
        s.setContent(format);
        s.setName(GenerateUtils.firstUpperCase(model.getPhysicalName()) +  MAPPER_XML_SUFFIX);
        s.setPackageInfo(packageInfo + PACKAGE_SUFFIX);
        return  s;
    }

    public String genFileContent() {
        return this.content;
    }

    public String getPackageInfo() {
        return this.packageInfo;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
    }
}
