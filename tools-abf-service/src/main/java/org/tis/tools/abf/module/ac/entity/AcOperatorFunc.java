package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import java.io.Serializable;

/**
 * acOperatorFunc针对人员配置的特殊权限，如特别开通的功能，或者特别禁止的功能
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_operator_func")
public class AcOperatorFunc implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * authType对应表字段
     */
    public static final String COLUMN_AUTH_TYPE = "auth_type";

    /**
     * startDate对应表字段
     */
    public static final String COLUMN_START_DATE = "start_date";

    /**
     * endDate对应表字段
     */
    public static final String COLUMN_END_DATE = "end_date";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * 操作员GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidOperator;

    /**
     * 功能GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidFunc;

    /**
     * 授权标志:取值来自业务菜单：DICT_AC_AUTHTYPE
     * 如：特别禁止、特别允许
     */
    public String authType;

    /**
     * 有效开始日期
     */
    public Date startDate;

    /**
     * 有效截至日期
     */
    public Date endDate;

    /**
     * 应用GUID:冗余字段
     */
    public String guidApp;

}

