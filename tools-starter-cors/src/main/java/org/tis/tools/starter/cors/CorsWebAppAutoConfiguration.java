package org.tis.tools.starter.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置跨域相关的Bean对象
 *
 * @author Shiyunlai
 * @since 2018-03-15
 */
@Configuration
@EnableConfigurationProperties(CorsConfigProperties.class)
public class CorsWebAppAutoConfiguration {

    @Autowired
    CorsConfigProperties corsConfigProperties;

    @Bean
    @ConditionalOnProperty(prefix = "tools.cors", name = "open", havingValue = "true")
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(corsConfigProperties.getMappingPath(), buildCorsConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildCorsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任何域名使用
        corsConfiguration.addAllowedOrigin(corsConfigProperties.getAllowedOrigins());
        // 允许任何头
        corsConfiguration.addAllowedHeader(corsConfigProperties.getAllowedHeaders());
        // 允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod(corsConfigProperties.getAllowedMethods());
        corsConfiguration.setAllowCredentials(corsConfigProperties.isAllowCredentials());
        // 跨域有效时间秒（超过后需要再次进行预验证 preflight ）
        corsConfiguration.setMaxAge(corsConfigProperties.getMaxAge());
        return corsConfiguration;
    }

//    效果与 corsFilter一样
//    @Bean
//    @ConditionalOnProperty(prefix = "tools.cors", name = "open", havingValue = "true")
//    public WebMvcConfigurer corsConfigurer() {
//
//        return new CorsWebMvcConfigurer(corsConfigProperties);
//    }

}
