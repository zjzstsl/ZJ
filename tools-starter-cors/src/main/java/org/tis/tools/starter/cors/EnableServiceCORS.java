package org.tis.tools.starter.cors;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CORSWebAppAutoConfiguration.class})
public @interface EnableServiceCORS {
}
