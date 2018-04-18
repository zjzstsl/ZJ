package org.tis.tools.abf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.tis.tools.starter.swagger.EnableSwagger2Doc;

/**
 * <pre>
 * Tools ABF Micro-Service 启动入口.
 * 基于Spring Boot,以jar方式启动
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-02
 */
@EnableSwagger2Doc
@SpringBootApplication
@ComponentScan("org.tis.tools")
public class ToolsAbfServiceApplication {

    private final static Logger logger = LoggerFactory.getLogger(ToolsAbfServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ToolsAbfServiceApplication.class, args);
        logger.info("Tools ABF Service Start Success!");
    }
}
