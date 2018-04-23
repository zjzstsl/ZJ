package org.tis.tools.asf.module.generate.engine.metadata.java.field;

import org.tis.tools.asf.module.biz.entity.enums.BizFieldType;
import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;

import java.util.ArrayList;
import java.util.List;

public class FieldBase extends ImportBase {

    private static final String PBSF = "public static final";

    private static final String PVSF = "private static final";

    private JavadocBase doc;

    private List<AnnotationBase> annotations;

    private String declaration;

    private String type;

    private String name;

    private String value;

    private Boolean stringType = true;

    public static FieldBase genSerialVersionUID() {
        FieldBase f = new FieldBase();
        f.setDoc(new JavadocBase("serialVersionUID."));
        f.setDeclaration(PVSF);
        f.setType("long");
        f.setName("serialVersionUID");
        f.setValue("1L");
        return f;
    }

    public static FieldBase genPSFS(String fieldDesc, String fieldName, String value) {
        FieldBase f = new FieldBase();
        f.setDoc(new JavadocBase(fieldDesc));
        f.setDeclaration(PBSF);
        f.setType(BizFieldType.STRING.value());
        f.setName(fieldName);
        f.setValue(value);
        return f;
    }

    public List<AnnotationBase> addAnno(AnnotationBase anno) {
        if (this.annotations == null) {
            setAnnotations(new ArrayList<>());
        }
        this.annotations.add(anno);
        return this.annotations;
    }

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

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
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

    public String getValue() {
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
