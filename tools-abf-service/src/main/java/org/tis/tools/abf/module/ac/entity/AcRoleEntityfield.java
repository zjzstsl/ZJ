package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * acRoleEntityfield角色与实体字段（属性）的对应关系。
 * 说明某个角色拥有哪些属性的操作权。
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_role_entityfield")
public class AcRoleEntityfield implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidRole对应表字段
     */
    public static final String COLUMN_GUID_ROLE = "guid_role";

    /**
     * guidEntityfield对应表字段
     */
    public static final String COLUMN_GUID_ENTITYFIELD = "guid_entityfield";

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
     * 拥有实体属性GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidEntityfield;

    /**
     * 可修改:取值来自业务菜单： DICT_YON
     */
    public String ismodify;

    /**
     * 可查看:取值来自业务菜单： DICT_YON
     */
    public String isview;

}

