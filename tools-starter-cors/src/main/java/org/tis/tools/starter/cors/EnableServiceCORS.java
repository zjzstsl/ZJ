package org.tis.tools.starter.cors;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <pre>
 * 允许服务开启跨域访问
 * 在Spring Boot启动类上注解 @EnableServiceCORS
 * 跨域知识： http://www.ruanyifeng.com/blog/2016/04/cors.html
 * 实现参考： https://www.jianshu.com/p/f2060a6d6e3b
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CorsWebAppAutoConfiguration.class})
public @interface EnableServiceCORS {
}
