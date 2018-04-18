package org.tis.tools.demo.module.a.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 性别 枚举
 * @author Tools Modeler
 * @since  2018-03-01 12:12:34 123
 */
public enum SexEnums implements IEnum{

    /**
     * 男
     */
    BOY(1, "男"),

    /**
     * 女
     */
    GIRL(0, "女"),

    /**
     * 其他
     */
    OTHER(2, "其他");

    private int value;
    private String name;

    SexEnums(int value, String name) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.toString();
    }

    @Override
    public String toString() {
        return "SexEnums{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
