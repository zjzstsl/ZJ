package com.company.scaffold.core.multidatasource.annotion;

import java.lang.annotation.*;

/**
 * 标识多数据源的注解
 *
 * 扩展Spring的AbstractRoutingDataSource抽象类实现多数据源
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:44:24
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {

    /**
     * 指定数据源名称
     * @return
     */
    String name() ; //default "";
}
