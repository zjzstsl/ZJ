package org.tis.tools.abf.module.om.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * omPositionnull
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Data
@TableName("om_position")
public class OmPosition implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOrg对应表字段
     */
    public static final String COLUMN_GUID_ORG = "guid_org";

    /**
     * positionCode对应表字段
     */
    public static final String COLUMN_POSITION_CODE = "position_code";

    /**
     * positionName对应表字段
     */
    public static final String COLUMN_POSITION_NAME = "position_name";

    /**
     * positionType对应表字段
     */
    public static final String COLUMN_POSITION_TYPE = "position_type";

    /**
     * positionStatus对应表字段
     */
    public static final String COLUMN_POSITION_STATUS = "position_status";

    /**
     * isleaf对应表字段
     */
    public static final String COLUMN_ISLEAF = "isleaf";

    /**
     * subCount对应表字段
     */
    public static final String COLUMN_SUB_COUNT = "sub_count";

    /**
     * positionLevel对应表字段
     */
    public static final String COLUMN_POSITION_LEVEL = "position_level";

    /**
     * positionSeq对应表字段
     */
    public static final String COLUMN_POSITION_SEQ = "position_seq";

    /**
     * guidParents对应表字段
     */
    public static final String COLUMN_GUID_PARENTS = "guid_parents";

    /**
     * startDate对应表字段
     */
    public static final String COLUMN_START_DATE = "start_date";

    /**
     * endDate对应表字段
     */
    public static final String COLUMN_END_DATE = "end_date";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId(value = "guid")
    public String guid;

    /**
     * 隶属机构GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidOrg;

    /**
     * 岗位代码:业务上对岗位的编码
     */
    public String positionCode;

    /**
     * 岗位名称
     */
    public String positionName;

    /**
     * 岗位类别:见业务字典： DICT_OM_POSITYPE
     * 机构岗位，工作组岗位
     */
    public String positionType;

    /**
     * 岗位状态:见业务字典： DICT_OM_POSISTATUS
     */
    public String positionStatus;

    /**
     * 是否叶子岗位:见业务字典： DICT_YON
     */
    public String isleaf;

    /**
     * 子节点数
     */
    public BigDecimal subCount;

    /**
     * 岗位层次
     */
    public BigDecimal positionLevel;

    /**
     * 岗位序列:岗位的面包屑定位信息
     */
    public String positionSeq;

    /**
     * 父岗位GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    public String guidParents;

    /**
     * 岗位有效开始日期
     */
    public Date startDate;

    /**
     * 岗位有效截止日期
     */
    public Date endDate;

}

