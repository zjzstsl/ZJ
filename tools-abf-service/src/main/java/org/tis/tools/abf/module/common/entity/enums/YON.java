package org.tis.tools.abf.module.common.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author zhaoch
 */

public enum YON implements IEnum {

    YES("Y", "是"),

    NO("N", "否");

    private final String value;

    private final String name;

    YON(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public Serializable getValue() {
        return this.value;
    }
}
