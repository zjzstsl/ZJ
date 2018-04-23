package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * omGroupApp工作组所拥有（允许操作）的应用列表
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("om_group_app")
public class OmGroupApp implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidGroup对应表字段
     */
    public static final String COLUMN_GUID_GROUP = "guid_group";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * 工作组GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidGroup;

    /**
     * 应用GUID
     */
    public String guidApp;

}

