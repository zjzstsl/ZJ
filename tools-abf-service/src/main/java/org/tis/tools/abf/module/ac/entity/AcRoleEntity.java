package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * acRoleEntity角色与数据实体的对应关系。
 * 说明角色拥有哪些实体操作权。
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_role_entity")
public class AcRoleEntity implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidRole对应表字段
     */
    public static final String COLUMN_GUID_ROLE = "guid_role";

    /**
     * guidEntity对应表字段
     */
    public static final String COLUMN_GUID_ENTITY = "guid_entity";

    /**
     * isadd对应表字段
     */
    public static final String COLUMN_ISADD = "isadd";

    /**
     * isdel对应表字段
     */
    public static final String COLUMN_ISDEL = "isdel";

    /**
     * ismodify对应表字段
     */
    public static final String COLUMN_ISMODIFY = "ismodify";

    /**
     * isview对应表字段
     */
    public static final String COLUMN_ISVIEW = "isview";

    /**
     * 角色GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidRole;

    /**
     * 拥有实体GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidEntity;

    /**
     * 可增加:取值来自业务菜单： DICT_YON
     */
    public String isadd;

    /**
     * 可删除:取值来自业务菜单： DICT_YON
     */
    public String isdel;

    /**
     * 可修改:取值来自业务菜单： DICT_YON
     */
    public String ismodify;

    /**
     * 可查看:取值来自业务菜单： DICT_YON
     */
    public String isview;

}

