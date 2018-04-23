package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * omPositionApp岗位所拥有（允许操作）的应用列表信息
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("om_position_app")
public class OmPositionApp implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidPosition对应表字段
     */
    public static final String COLUMN_GUID_POSITION = "guid_position";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * 岗位GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidPosition;

    /**
     * 应用GUID
     */
    public String guidApp;

}

