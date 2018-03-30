package org.tis.tools.abf.module.sys.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum SeqnoReset implements IEnum {

    EVER("E", "不重置", "连续使用，不重置")	,

    DAY("D", "按天重置", "指定指定重置时间点HH:mm，如果不指定，系统默认每天01:00执行序号资源重置"),

    WEEK("W", "按周重置", "可以指定每周的那天何时重置，如果不指定，系统默认为每周六凌晨01:00执行序号资源重置"),

    CUSTOM("C", "自定义重置周期", "由用户设置重置周期");

    private final String value;

    private final String name;

    private final String desc;

    SeqnoReset(final String value, final String name, final String desc) {
        this.value = value;
        this.name = name;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Serializable getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
