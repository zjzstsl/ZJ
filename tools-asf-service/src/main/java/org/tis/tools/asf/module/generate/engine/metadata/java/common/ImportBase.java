package org.tis.tools.asf.module.generate.engine.metadata.java.common;

import java.io.Serializable;
import java.util.Set;

public abstract class ImportBase implements Serializable {
    private static final long serialVersionUID = 1L;

    private String[] importClassName;

    public String[] getImportClassName() {
        return importClassName;
    }

    public void setImportClassName(String[] importClassName) {
        this.importClassName = importClassName;
    }

}
