package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * omEmpGroup定义工作组包含的人员（工作组中有哪些人员）
 * 如：某个项目组有哪些人员
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("om_emp_group")
public class OmEmpGroup implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidEmp对应表字段
     */
    public static final String COLUMN_GUID_EMP = "guid_emp";

    /**
     * guidGroup对应表字段
     */
    public static final String COLUMN_GUID_GROUP = "guid_group";

    /**
     * 员工GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidEmp;

    /**
     * 隶属工作组GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidGroup;

}

