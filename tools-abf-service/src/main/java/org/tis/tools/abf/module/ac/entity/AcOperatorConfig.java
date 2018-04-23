package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * acOperatorConfig操作员个性化配置
 * 如颜色配置
 *     登录风格
 *     是否使用重组菜单
 *     默认身份
 *     等
 * 
 * “操作员＋应用系统”，将配置按应用系统进行区分。
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_operator_config")
public class AcOperatorConfig implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * guidConfig对应表字段
     */
    public static final String COLUMN_GUID_CONFIG = "guid_config";

    /**
     * configValue对应表字段
     */
    public static final String COLUMN_CONFIG_VALUE = "config_value";

    /**
     * 操作员GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidOperator;

    /**
     * 配置GUID
     */
    public String guidConfig;

    /**
     * 配置值
     */
    public String configValue;

}

