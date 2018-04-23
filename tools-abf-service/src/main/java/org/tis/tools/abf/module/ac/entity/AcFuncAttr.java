package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * acFuncAttr功能表字段之外的属性
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_func_attr")
public class AcFuncAttr implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * attrType对应表字段
     */
    public static final String COLUMN_ATTR_TYPE = "attr_type";

    /**
     * attrKey对应表字段
     */
    public static final String COLUMN_ATTR_KEY = "attr_key";

    /**
     * attrValue对应表字段
     */
    public static final String COLUMN_ATTR_VALUE = "attr_value";

    /**
     * memo对应表字段
     */
    public static final String COLUMN_MEMO = "memo";

    /**
     * 对应功能GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidFunc;

    /**
     * 属性类型
     */
    public String attrType;

    /**
     * 属性名
     */
    public String attrKey;

    /**
     * 属性名
     */
    public String attrValue;

    /**
     * 备注
     */
    public String memo;

}

