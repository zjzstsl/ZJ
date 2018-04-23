package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * acOperatorShortcut针对应用系统定义快捷菜单
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_operator_shortcut")
public class AcOperatorShortcut implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * shortcutKey对应表字段
     */
    public static final String COLUMN_SHORTCUT_KEY = "shortcut_key";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * aliasFuncName对应表字段
     */
    public static final String COLUMN_ALIAS_FUNC_NAME = "alias_func_name";

    /**
     * orderNo对应表字段
     */
    public static final String COLUMN_ORDER_NO = "order_no";

    /**
     * imagePath对应表字段
     */
    public static final String COLUMN_IMAGE_PATH = "image_path";

    /**
     * expandPath对应表字段
     */
    public static final String COLUMN_EXPAND_PATH = "expand_path";

    /**
     * 操作员GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidOperator;

    /**
     * 快捷按键:如：CTRL+1 表示启动TX010505，本字段记录 CTRL+1 这个信息
     */
    public String shortcutKey;

    /**
     * 应用GUID:冗余字段，方便为快捷键分组
     */
    public String guidApp;

    /**
     * 功能GUID
     */
    public String guidFunc;

    /**
     * 功能别名
     */
    public String aliasFuncName;

    /**
     * 排列顺序:原类型smallint
     */
    public BigDecimal orderNo;

    /**
     * 快捷菜单图片路径:操作员自定义时指定，不指定则保持
     */
    public String imagePath;

    /**
     * 菜单展开图片路径:自定时指定，不指定则保持
     */
    public String expandPath;

}

