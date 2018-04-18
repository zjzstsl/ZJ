package org.tis.tools.asf.module.biz.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.List;

/**
 * describe: 业务模型对象
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Data
@TableName("biz_model")
public class BizModel {

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_MODULE_ID = "module_id";

    public static final String COLUMN_PHYSICAL_NAME = "physical_name";

    public static final String COLUMN_LOGICAL_NAME = "logical_name";

    public static final String COLUMN_DESC = "desc";


    @TableId
    private String id;

    private String moduleId;

    /**
     * 物理名称，对应属 如人员对象的姓名属性的物理名称为 name
     */
    private String physicalName;

    /**
     * 逻辑名称，对应表名的解释 如人员对象的姓名属行名称为 人员姓名
     */
    private String logicalName;

    /**
     * 描述
     */
    private String desc;

    @TableField(exist = false)
    private List<BizField> fieldList;
}
