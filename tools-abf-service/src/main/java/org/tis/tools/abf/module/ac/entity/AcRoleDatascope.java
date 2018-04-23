package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * acRoleDatascopenull
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_role_datascope")
public class AcRoleDatascope implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidRole对应表字段
     */
    public static final String COLUMN_GUID_ROLE = "guid_role";

    /**
     * guidDatascope对应表字段
     */
    public static final String COLUMN_GUID_DATASCOPE = "guid_datascope";

    /**
     * 角色GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidRole;

    /**
     * 拥有数据范围GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidDatascope;

}

