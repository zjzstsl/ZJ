package com.company.scaffold.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.company.scaffold.base.constant.DatasourceEnum;
import com.company.scaffold.core.datasource.config.DruidProperties;
import com.company.scaffold.core.multidatasource.DynamicDataSource;
import com.company.scaffold.core.multidatasource.config.MultiDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * <pre>
 * 自动配置MybatisPlus
 * 注解 @EnableTransactionManagement(order=2) 由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面*
 * </pre>
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.company.scaffold.*.dao"})
public class MybatisPlusAutoConfiguration {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MultiDataSourceProperties mutiDataSourceProperties;

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

        DruidDataSource defaultDataSource = defaultDataSource();
        DruidDataSource bizDataSource = bizDataSource();

        try {
            defaultDataSource.init();
            bizDataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap(2);
        hashMap.put(DatasourceEnum.DATA_SOURCE_DEF, defaultDataSource);
        hashMap.put(DatasourceEnum.DATA_SOURCE_BIZ, bizDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    @ConditionalOnProperty(prefix = "tools", name = "mybatis-performance-open", havingValue = "true")
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * 注入sql注入器
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     * 另一个数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
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

//    /**
//     * 数据范围mybatis插件
//     */
//    TODO 结合ABF对数据范围的控制设计实现
//    @Bean
//    public DataScopeInterceptor dataScopeInterceptor() {
//        return new DataScopeInterceptor();
//    }

}
