package org.tis.tools.asf.module.er.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;
import org.tis.tools.asf.module.biz.entity.enums.BizFieldType;

import java.io.Serializable;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/8
 **/
public enum ERColumnType implements BaseEnum {

    BIGINT("bigint", BizFieldType.LONG),

    INT("int(n)", BizFieldType.INTEGER),

    CHAR("char", BizFieldType.STRING),

    VARCHAR("varchar(n)", BizFieldType.STRING),

    TEXT("text", BizFieldType.STRING),

    BLOB("blob", BizFieldType.BLOB),

    DATE("date", BizFieldType.DATE),

    TIME("time", BizFieldType.DATE),

    DATETIME("datetime", BizFieldType.DATE),

    TIMESTAMP("timestamp", BizFieldType.DATE),

    DECIMAL_P("decimal(p)", BizFieldType.BIGDECIMAL),

    DECIMAL("decimal", BizFieldType.BIGDECIMAL),

    DOUBLE("double", BizFieldType.DOUBLE),

    FLOAT("float", BizFieldType.FLOAT),

    INTEGER("integer", BizFieldType.INTEGER),

    CHARACTER("character(n)", BizFieldType.STRING),

    NUMERIC("numeric(p)", BizFieldType.BIGDECIMAL);

    private String value;

    private BizFieldType bizType;

    ERColumnType(String value, BizFieldType bizType) {
        this.value = value;
        this.bizType = bizType;
    }

    public BizFieldType getFieldType() {
        return bizType;
    }

    public static ERColumnType getColumnType(String value) {
        if (value == null) {
            return null;
        } else {
            for (ERColumnType columnType : ERColumnType.values()) {
                if (columnType.getValue().equals(value)) {
                    return columnType;
                }
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Serializable getValue() {
        return value;
    }

    @Override
    public Serializable deserialze() {
        return value;
    }
}
