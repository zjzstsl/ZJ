package org.tis.tools.demo.module.a.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.tis.tools.demo.module.a.entity.enums.SexEnums;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体 Demo
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 *
 */
@Data
@ToString
@ApiModel("Demo实体")
@TableName("scaffold_demo")
public class Demo extends Model<Demo>{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("全局记录ID")
    String guid ;
    @ApiModelProperty("d名称")
    @TableField("dName") //数据库字段命名以小驼峰规则，减少字段名称转换的处理耗时
    String dName ;
    @ApiModelProperty("d年龄")
    Integer dAge ;
    @ApiModelProperty("d生日")
    Date dBirthday ;
    @ApiModelProperty("d性别")
    SexEnums dSex ;

    @Override
    protected Serializable pkVal() {
        return this.guid;
    }
}
