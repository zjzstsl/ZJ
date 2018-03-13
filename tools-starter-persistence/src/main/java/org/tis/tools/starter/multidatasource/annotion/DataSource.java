package org.tis.tools.starter.multidatasource.annotion;

import java.lang.annotation.*;

/**
 * <pre>
 * 标识多数据源的注解
 * 扩展Spring的AbstractRoutingDataSource抽象类实现多数据源
 * 一般用在Mapper，或Service的方法上，为接下来执行的SQL指定了数据源
 * </pre>
 *
 * @author fengshuonan
 * @author Shiyunlai
 * @since 2017年3月5日 上午9:44:24
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {

    /**
     * 指定数据源名称
     * 默认为 default
     *
     * @return
     */
    String name() default "default";
}
