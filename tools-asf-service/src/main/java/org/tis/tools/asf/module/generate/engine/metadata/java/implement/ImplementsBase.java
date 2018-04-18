package org.tis.tools.asf.module.generate.engine.metadata.java.implement;

import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;

public class ImplementsBase extends ImportBase {

    private String name;

    public ImplementsBase(String name, String... importClassName) {
        this.name = name;
        this.setImportClassName(importClassName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
