package org.tis.tools.abf.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;
import org.tis.tools.abf.demo.entity.enums.SexEnums;

import java.io.Serializable;
import java.util.Date;

/**
 * Demo
 * <p>
 * XXX 实体
 * <p>
 * 开发规范：
 * 1.继承mybaits的Model
 * 2.属性命名遵守小驼峰规则
 * 3.可以使用lombok
 *
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
@Data
@ToString
@TableName("scaffold_demo")
public class Demo extends Model<Demo> {

    private static final long serialVersionUID = 1L;

    String guid;
    //@TableField("dName") //数据库字段命名以小驼峰规则，减少字段名称转换的处理耗时
    String dName;
    Integer dAge;
    Date dBirthday;
    SexEnums dSex;

    @Override
    protected Serializable pkVal() {
        return this.guid;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "guid='" + guid + '\'' +
                ", dName='" + dName + '\'' +
                ", dAge=" + dAge +
                ", dSex=" + dSex +
                ", dBirthday=" + dBirthday +
                '}';
    }
}
