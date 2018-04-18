package org.tis.tools.asf.module.biz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.asf.module.biz.entity.enums.BizFieldType;

/**
 * describe: 业务模型对象
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Data
@TableName("biz_field")
public class BizField {

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_ORDER = "order";

    public static final String COLUMN_MODEL_ID = "model_id";

    public static final String COLUMN_DESC = "desc";

    public static final String COLUMN_LOGICAL_NAME = "logical_name";

    public static final String COLUMN_PHYSICAL_NAME = "physical_name";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_LENGTH = "length";

    public static final String COLUMN_DEFAULT_VALUE = "default_value";

    public static final String COLUMN_ENUMS = "String";

    /**
     * 标识
     */
    @TableId
    private String id;

    /**
     * 顺序
     */
    private Integer order;

    /**
     * 所属表
     */
    private String modelId;

    /**
     * 描述
     */
    private String desc;

    /**
     * 逻辑名称
     */
    private String logicalName;

    /**
     * 物理名称
     */
    private String physicalName;

    /**
     * 字段类型
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private BizFieldType type;

    /**
     * 字段长度
     */
    private Integer length;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 对应枚举类
     */
    private String enums;

    /**
     * 是否为主键
     */
    private Boolean primaryKey;
}
