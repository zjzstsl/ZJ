package org.tis.tools.asf.module.generate.engine.metadata.java.extend;

import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;

public class ExtendsBase extends ImportBase {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExtendsBase(String name, String... importClassName) {
        this.name = name;
        this.setImportClassName(importClassName);
    }
}
