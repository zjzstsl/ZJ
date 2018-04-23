package org.tis.tools.abf.module.common.log;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * describe: 操作日志的操作类型
 *
 * @author zhaoch
 * @date 2018/4/20
 **/
public enum OperateType implements BaseEnum {
    ADD("add", "新增"),
    DELETE("delete", "删除"),
    UPDATE("update", "修改"),
    QUERY("query", "查询");

    private final String value;

    private final String name;

    OperateType(final String value, final String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Serializable deserialze() {
        return value;
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
