package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * omEmpPosition定义人员和岗位的对应关系，需要注明，一个人员可以设定一个基本岗位
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("om_emp_position")
public class OmEmpPosition implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guidEmp对应表字段
     */
    public static final String COLUMN_GUID_EMP = "guid_emp";

    /**
     * guidPosition对应表字段
     */
    public static final String COLUMN_GUID_POSITION = "guid_position";

    /**
     * ismain对应表字段
     */
    public static final String COLUMN_ISMAIN = "ismain";

    /**
     * 员工GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidEmp;

    /**
     * 所在岗位GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidPosition;

    /**
     * 是否主岗位:取值来自业务菜单：DICT_YON
     * 只能有一个主岗位
     */
    public String ismain;

}

