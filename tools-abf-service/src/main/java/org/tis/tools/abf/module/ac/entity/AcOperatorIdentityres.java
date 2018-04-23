package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * acOperatorIdentityres身份是操作员权限集合的子集，限定操作员登陆某个应用时，只具备特定的功能；
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("ac_operator_identityres")
public class AcOperatorIdentityres implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidIdentity对应表字段
     */
    public static final String COLUMN_GUID_IDENTITY = "guid_identity";

    /**
     * acResourcetype对应表字段
     */
    public static final String COLUMN_AC_RESOURCETYPE = "ac_resourcetype";

    /**
     * guidAcResource对应表字段
     */
    public static final String COLUMN_GUID_AC_RESOURCE = "guid_ac_resource";

    /**
     * 身份GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidIdentity;

    /**
     * 资源类型:资源：操作员所拥有的权限来源
     * 见业务字典： DICT_AC_RESOURCETYPE
     * function 功能
     * role 角色
     */
    public String acResourcetype;

    /**
     * 资源GUID:根据资源类型对应到不同权限资源的GUID
     */
    public String guidAcResource;

}

