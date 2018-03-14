package org.tis.tools.starter.cors;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 支持跨域
 * @author Shiyunlai
 */
@EnableWebMvc
@Configuration
public class ConfigWebApp extends WebMvcConfigurationSupport {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
        super.addCorsMappings(registry);
    }
}
