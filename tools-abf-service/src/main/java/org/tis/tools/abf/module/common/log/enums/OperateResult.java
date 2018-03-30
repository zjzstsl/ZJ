package org.tis.tools.abf.module.common.log.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum OperateResult implements IEnum {


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
}
