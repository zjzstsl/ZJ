package org.tis.tools.asf.module.biz.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * describe: 业务模型对象属性类型枚举类
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
public enum  BizFieldType implements BaseEnum {

    LONG("Long", null),

    INTEGER("Integer", null),

    STRING("String", null),

    BLOB("Blob", null),

    DATE("Date", "java.util.Date"),

    TIME("Time", "java.sql.Time"),

    BIGDECIMAL("BigDecimal", "java.math.BigDecimal"),

    DOUBLE("Double", null),

    FLOAT("Float", null);

    private String value;

    private String imports;

    BizFieldType(String value, String imports) {
        this.value = value;
        this.imports = imports;
    }

    @Override
    public Serializable getValue() {
        return value;
    }

    public String getImports() {
        return imports;
    }

    public String value() {
        return value;
    }


    @Override
    public Serializable deserialze() {
        return value;
    }
}
