package org.tis.tools.asf.module.generate.engine.metadata.java.method;

import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;

import java.util.List;

public class Parameter extends ImportBase {

    private List<AnnotationBase> annotations;

    private String type;

    private String name;

    public List<AnnotationBase> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationBase> annotations) {
        this.annotations = annotations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
