package org.tis.tools.asf.module.generate.engine.metadata.java.annoation;

import org.tis.tools.asf.module.generate.engine.metadata.java.common.FastBuilder;
import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnnotationBase extends ImportBase {

    private String name;

    private String value;

    private Boolean stringType = true;

    private List<AnnotationField> fields;

    public AnnotationBase() {
    }

    public AnnotationBase(String name, String importClassName) {
        this.name = name;
        this.setImportClassName(new String[]{importClassName});
    }

    public AnnotationBase(String name, String value, String importClassName) {
        this.name = name;
        this.value = value;
        this.setImportClassName(new String[]{importClassName});
    }

    public AnnotationBase(String name, List<AnnotationField> fields, String importClassName) {
        this.name = name;
        this.fields = fields;
        this.setImportClassName(new String[]{importClassName});
    }

    public AnnotationBase(String name, String value, Boolean stringType, String... importClassName) {
        this.name = name;
        this.value = value;
        this.stringType = stringType;
        this.setImportClassName(importClassName);
    }

    public List<AnnotationField> addAnnotationField(AnnotationField field) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.add(field);
        return this.fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AnnotationField> getFields() {
        return fields;
    }

    public void setFields(List<AnnotationField> fields) {
        this.fields = fields;
    }

    public static AnnotationBase create() {
        return new AnnotationBase();
    }

    public Boolean getStringType() {
        return stringType;
    }

    public void setStringType(Boolean stringType) {
        this.stringType = stringType;
    }
}
