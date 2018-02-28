package org.tis.tools.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * <pre>
 * 配置中心启动入口
 * 注解 @SpringBootApplication 作为Spring Boot工程
 * 注解 @EnableConfigServer 作为Config Server
 * 注解 @EnableDiscoveryClient 向服务注册中心注册，其服务可被发现
 * </pre>
 *
 * @author Shiyunlai
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ToolsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsConfigServerApplication.class, args);
	}
}
