package org.tis.toolsfortest.usestarter.persistence.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.tis.toolsfortest.usestarter.persistence.demo.entity.enums.SexEnums;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体 Demo
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 *
 */
@TableName("scaffold_demo")
public class Demo extends Model<Demo>{

    private static final long serialVersionUID = 1L;

    String guid ;
    @TableField("dName") //数据库字段命名以小驼峰规则，减少字段名称转换的处理耗时
    String dName ;
    Integer dAge ;
    Date dBirthday ;
    SexEnums dSex ;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public Integer getdAge() {
        return dAge;
    }

    public void setdAge(Integer dAge) {
        this.dAge = dAge;
    }

    public Date getdBirthday() {
        return dBirthday;
    }

    public void setdBirthday(Date dBirthday) {
        this.dBirthday = dBirthday;
    }

    public SexEnums getdSex() {
        return dSex;
    }

    public void setdSex(SexEnums dSex) {
        this.dSex = dSex;
    }

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
