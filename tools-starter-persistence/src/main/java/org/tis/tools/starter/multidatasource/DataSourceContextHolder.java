package org.tis.tools.starter.multidatasource;

import java.util.ArrayList;
import java.util.List;

/**
 * datasource的上下文
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:10:58
 */
public class DataSourceContextHolder {

    /**
     * 储存当线程使用的数据源名称
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 存储所有数据源名称
     */
    private static final List<String> datasourceNames = new ArrayList<String>();

    /**
     * 设置数据源类型
     *
     * @param dataSourceType 数据库类型
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取数据源类型
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 清除数据源类型
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 存储数据源名称
     *
     * @param datasourceName
     */
    public static void registerName(String datasourceName) {
        datasourceNames.add(datasourceName);
    }

    /**
     * 获取所有数据源名称
     *
     * @return
     */
    public static List<String> getDatasourceNames() {
        return datasourceNames;
    }

    /**
     * 判断是否存在某个数据源
     *
     * @param datasourceName
     * @return
     */
    public static boolean contains(String datasourceName) {
        for (String dsn : datasourceNames) {
            if (datasourceName.equals(dsn)) {
                return true;
            }
        }
        return false;
    }
}
