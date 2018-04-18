package org.tis.tools.asf.module.er.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.validation.AddValidateGroup;

import java.util.List;

/**
 * ER图的分类模块，如常见的 组织管理、权限管理等等
 * 对应位置 diagram/settings/category_settings/categories/category
 * @author zhaoch
 */
@Data
@TableName("er_category")
public class ERCategory {

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_APP_ID = "app_id";

    public static final String COLUMN_NAME = "name";

    /**
     * ID 标识
     */
    @TableId
    private String id;

    /**
     * 应用ID
     */
    @NotBlank(groups = {AddValidateGroup.class}, message = "应用ID不能为空")
    private String appId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类下的表集合
     */
    @JSONField(name = "node_element")
    @TableField(exist = false)
    private List<String> tableIds;


    @TableField(exist = false)
    private List<ERTable> tableList;

}
