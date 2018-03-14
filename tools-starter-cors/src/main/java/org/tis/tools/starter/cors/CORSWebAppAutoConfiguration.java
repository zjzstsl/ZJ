package org.tis.tools.starter.cors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置跨域相关的Bean对象
 * @author Shiyunlai
 */
@Configuration
public class CORSWebAppAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "tools.cors.enabled", matchIfMissing = true)
    public WebMvcConfigurationSupport openCORS(){
       return new ConfigWebApp() ;
    }
}
