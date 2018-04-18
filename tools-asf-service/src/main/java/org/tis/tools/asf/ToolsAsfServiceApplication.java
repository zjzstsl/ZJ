package org.tis.tools.asf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <pre>
 * Tools ASF Micro-Service 启动入口.
 * 基于Spring Boot,以jar方式启动
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-02
 */
@SpringBootApplication
@ComponentScan("org.tis.tools")
public class ToolsAsfServiceApplication {

    private final static Logger logger = LoggerFactory.getLogger(ToolsAsfServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ToolsAsfServiceApplication.class, args);
        logger.info("Tools ASF Service Start Success!");
    }
}
