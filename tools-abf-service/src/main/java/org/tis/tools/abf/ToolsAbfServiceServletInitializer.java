package org.tis.tools.abf;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * <pre>
 * Tools ABF Micro-Service 启动入口.
 * 基于Spring Boot,以War方式启动.
 * 需修改 pom.xml 中 package为war，重新编译打包 mvn clean package
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-02
 */
public class ToolsAbfServiceServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ToolsAbfServiceApplication.class);
    }
}
