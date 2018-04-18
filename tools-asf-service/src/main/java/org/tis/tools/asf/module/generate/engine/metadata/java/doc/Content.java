package org.tis.tools.asf.module.generate.engine.metadata.java.doc;

public class Content {

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Content(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
