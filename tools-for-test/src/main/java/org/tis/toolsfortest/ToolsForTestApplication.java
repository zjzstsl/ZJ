package org.tis.toolsfortest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tis.tools.starter.cors.EnableServiceCORS;
import org.tis.tools.starter.swagger.EnableSwagger2Doc;

/**
 * 应用启动入口
 * 注解 @EnableSwagger2Doc ，启用swagger功能
 * 注解 @SpringBootApplication 启动spring boot 应用
 *
 * @author Shiyunlai
 */
@EnableSwagger2Doc
@EnableServiceCORS
@SpringBootApplication
public class ToolsForTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsForTestApplication.class, args);
    }
}
