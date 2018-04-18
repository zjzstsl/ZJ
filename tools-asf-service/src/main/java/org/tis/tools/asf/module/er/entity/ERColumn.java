package org.tis.tools.asf.module.er.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.tis.tools.asf.module.er.entity.enums.ERColumnType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

@Data
@TableName("er_column")
public class ERColumn {

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_ORDER = "order";

    public static final String COLUMN_TABLE_ID = "table_id";

    public static final String COLUMN_DESC = "desc";

    public static final String COLUMN_LOGICAL_NAME = "logical_name";

    public static final String COLUMN_PHYSICAL_NAME = "physical_name";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_LENGTH = "length";

    public static final String COLUMN_DEFAULT_VALUE = "default_string";

    public static final String COLUMN_AUTO_INCREMENT = "auto_increment";

    public static final String COLUMN_FOREIGN_KEY = "foreign_key";

    public static final String COLUMN_NOT_NULL = "not_null";

    public static final String COLUMN_PRIMARY_KEY = "primary_key";

    public static final String COLUMN_UNIQUE_KEY = "unique_key";

    /**
     * 对应模型数据字典的id {@link ERWord#getId()}
     */
    @TableField(exist = false)
    private String wordId;

    /**
     * 对应模型数据字典的id {@link ERWord#getId()}
     */
    @TableField(exist = false)
    private String referencedColumn;

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
    private String tableId;

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
     * TODO 如果这样的反序列化需求很多，配置显得很麻烦
     * 有相同的需求在此提出，似乎还没有解决方法，暂时需要每个如下配置 https://github.com/alibaba/fastjson/issues/1301
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private ERColumnType type;

    /**
     * 字段长度
     */
    private Integer length;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是否自增 false 、true
     */
    private Boolean autoIncrement;

    /**
     * 是否外键
     */
    private Boolean foreignKey;

    /**
     * 是否不能为空
     */
    private Boolean notNull;

    /**
     * 是否为主键
     */
    private Boolean primaryKey;

    /**
     * 是否唯一
     */
    private Boolean uniqueKey;

}
