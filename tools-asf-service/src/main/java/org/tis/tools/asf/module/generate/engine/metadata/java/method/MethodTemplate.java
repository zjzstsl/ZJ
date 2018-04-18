package org.tis.tools.asf.module.generate.engine.metadata.java.method;

import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;

import java.util.Set;

public class MethodTemplate extends ImportBase {

    private String text;

    public MethodTemplate(String text, String... importClassName) {
        this.text = text;
        this.setImportClassName(importClassName);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
