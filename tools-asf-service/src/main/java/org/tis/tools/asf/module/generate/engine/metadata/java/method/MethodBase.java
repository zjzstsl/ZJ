package org.tis.tools.asf.module.generate.engine.metadata.java.method;

import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.implement.ImplementsBase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MethodBase extends ImportBase {

    private String name;

    private JavadocBase doc;

    private List<AnnotationBase> annotations;

    private MethodTemplate template;

    public JavadocBase getDoc() {
        return doc;
    }

    public void setDoc(JavadocBase doc) {
        this.doc = doc;
    }

    public List<AnnotationBase> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationBase> annotations) {
        this.annotations = annotations;
    }

    public MethodTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MethodTemplate template) {
        this.template = template;
    }
}
