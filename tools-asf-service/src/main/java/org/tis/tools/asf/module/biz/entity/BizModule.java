package org.tis.tools.asf.module.biz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.validation.AddValidateGroup;


import java.util.List;

/**
 * describe: 业务模型分类
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Data
@TableName("biz_module")
public class BizModule {

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
     * 分类物理名称
     */
    private String physicalName;

    /**
     * 分类逻辑名称
     */
    private String logicName;

    @TableField(exist = false)
    private List<BizModel> modelList;

}
