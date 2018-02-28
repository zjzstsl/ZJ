package org.tis.tools.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <pre>
 * 应用启动入口: Tools的服务注册中心
 *
 * 注解 @EnableEurekaServer 作为服务注册中心
 * 注解 @SpringBootApplication 以 Spring Boot 方式启动
 * </pre>
 *
 * @author Shiyunlai
 */
@EnableEurekaServer
@SpringBootApplication
public class ToolsEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsEurekaServerApplication.class, args);
    }
}
