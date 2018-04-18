package org.tis.tools.asf.module.generate.engine.metadata.java.annoation;

import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;

/**
 * 注解配置
 */
public class AnnotationField extends ImportBase {

    /**
     * 配置名
     */
    private String key;

    /**
     * 配置值
     */
    private String value;

    private Boolean stringType = true;


    public AnnotationField() {
    }

    public AnnotationField(String key, String value, Boolean stringType) {
        this.key = key;
        this.value = value;
        this.stringType = stringType;
    }

    public AnnotationField(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public AnnotationField(String key, String value, String... importClassName) {
        this.key = key;
        this.value = value;
        setImportClassName(importClassName);
    }

    public AnnotationField(String key, String value, Boolean stringType, String... importClassName) {
        this.key = key;
        this.value = value;
        this.stringType = stringType;
        setImportClassName(importClassName);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getStringType() {
        return stringType;
    }

    public void setStringType(Boolean stringType) {
        this.stringType = stringType;
    }
}
