package org.tis.tools.asf;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * <pre>
 * 以War方式启动时的入口
 * 1.修改 pom.xml 中 package为war
 * 2.重新编译打包 mvn clean package
 * 3.生成的war包可部署到web容器(如：tomcat）中运行
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-02
 */
public class ToolsAsfServiceServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ToolsAsfServiceApplication.class);
    }
}
