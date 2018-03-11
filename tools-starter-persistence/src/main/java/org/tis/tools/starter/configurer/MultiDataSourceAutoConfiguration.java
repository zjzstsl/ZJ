package org.tis.tools.starter.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tis.tools.starter.multidatasource.DynamicDataSource;
import org.tis.tools.starter.multidatasource.config.MultiDataSourceProperties;
import org.tis.tools.starter.mybatisplus.config.DruidProperties;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * <pre>
 * 自动配置数据源，多数据源（使用 Spring AbstractRoutingDataSource 实现）
 * 注解 @EnableTransactionManagement(order=2) 由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
 * 注解 @EnableConfigurationProperties({DruidProperties.class,MultiDataSourceProperties.class})//开启属性注入,通过@autowired注入
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-11
 */
@Configuration
@EnableConfigurationProperties({DruidProperties.class,MultiDataSourceProperties.class})
@EnableTransactionManagement(order = 2)
public class MultiDataSourceAutoConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(MultiDataSourceAutoConfiguration.class);

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MultiDataSourceProperties multiDataSourceProperties;

    /**
     * 配置单数据源连接池
     */
    @Bean
    @ConditionalOnProperty(prefix = "tools", name = "multi-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return defaultDataSource();
    }

    /**
     * 配置多数据源连接池
     */
    @Bean
    @ConditionalOnProperty(prefix = "tools", name = "multi-datasource-open", havingValue = "true")
    public DynamicDataSource multiDataSource() {
        logger.info("启用多数据源");

        DruidDataSource defaultDataSource = defaultDataSource();
        DruidDataSource otherDataSource = bizDataSource();

        try {
            defaultDataSource.init();
            otherDataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap(2);
        hashMap.put(defaultDataSource.getName(), defaultDataSource);
        hashMap.put(otherDataSource.getName(), otherDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        return dynamicDataSource;
    }

    /**
     * 另一个数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        multiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 默认数据源
     */
    private DruidDataSource defaultDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }
}
