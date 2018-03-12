package org.tis.tools.starter.multidatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:11:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("当前使用数据源：" + DataSourceContextHolder.getDataSourceType()==null?"默认数据源":DataSourceContextHolder.getDataSourceType());
        return DataSourceContextHolder.getDataSourceType();
    }
}
