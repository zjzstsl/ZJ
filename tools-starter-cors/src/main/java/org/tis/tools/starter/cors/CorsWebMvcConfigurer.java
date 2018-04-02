package org.tis.tools.starter.cors;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 设置跨域配置
 * @author zhaochunhai
 * @author Shiyunlai
 * @since 2018-03-15
 */
//public class CorsWebMvcConfigurer implements WebMvcConfigurer{
public class CorsWebMvcConfigurer  extends WebMvcConfigurerAdapter{

    CorsConfigProperties corsConfigProperties;

    public CorsWebMvcConfigurer(CorsConfigProperties corsConfigProperties) {
        this.corsConfigProperties = corsConfigProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsConfigProperties.getMappingPath())
                .allowedOrigins(corsConfigProperties.allowedOrigins)
                .allowCredentials(corsConfigProperties.isAllowCredentials())
                .allowedHeaders(corsConfigProperties.getAllowedHeaders())
                .allowedMethods(corsConfigProperties.getAllowedMethods())
                .maxAge(corsConfigProperties.getMaxAge());
    }
}
