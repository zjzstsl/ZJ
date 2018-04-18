package org.tis.tools.abf.module.common.log.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

public enum OperateResult implements BaseEnum {


    SUCCESS("s", "成功"),
    FAILURE("f", "失败"),
    ERROR("e", "错误");

    private final String value;
    private final String desc;

    OperateResult(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public Serializable getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


    @Override
    public Serializable deserialze() {
        return value;
    }
}
