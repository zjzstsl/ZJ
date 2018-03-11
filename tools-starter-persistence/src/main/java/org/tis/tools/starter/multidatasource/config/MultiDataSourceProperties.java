package org.tis.tools.starter.multidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 多数据源配置
 * 默认了参数，如需修改请在yml中配置
 *
 * @author fengshuonan
 * @date 2017-08-16 10:02
 */
//TODO 增强为可在yml中随意配置多个数据源
@Component
@ConfigurationProperties(prefix = "tools.multi-datasource")
public class MultiDataSourceProperties {

    /**
     * 数据源名称，如果不指定默认使用 other
     */
    private String datasourceName = "other";

    private String url = "jdbc:mysql://127.0.0.1:3306/biz?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

    private String username = "root";

    private String password = "root";

    private String driverClassName = "com.mysql.jdbc.Driver";

    private String validationQuery = "SELECT 'x'";

    public void config(DruidDataSource dataSource) {
        dataSource.setName(datasourceName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setValidationQuery(validationQuery);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String dataSourceName) {
        this.datasourceName = dataSourceName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }
}
