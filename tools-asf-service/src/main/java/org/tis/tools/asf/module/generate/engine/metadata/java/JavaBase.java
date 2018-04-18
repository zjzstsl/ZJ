package org.tis.tools.asf.module.generate.engine.metadata.java;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.tis.tools.asf.module.biz.entity.enums.BizFieldType;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationField;
import org.tis.tools.asf.module.generate.engine.metadata.java.common.ImportBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.Content;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.extend.ExtendsBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.field.FieldBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.implement.ImplementsBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.method.MethodBase;

import java.text.SimpleDateFormat;
import java.util.*;


public class JavaBase {

    private String packageInfo;

    private Set<String> imports = new HashSet<>();

    private JavadocBase doc;

    private List<AnnotationBase> annotations;

    private String declaration;

    private String type;

    private String name;

    private ExtendsBase extend;

    private List<ImplementsBase> impls;

    private List<FieldBase> fields;

    private List<MethodBase> methods;

    public String genFileContent() {
        return wrapPackage() +
                wrapImport() +
                wrapJavadoc() +
                wrapAnnotation() +
                wrapClass() +
                wrapField() +
                wrapMethod() +
                "}\n";
    }

    public String getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
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

    public List<FieldBase> getFields() {
        return fields;
    }

    public void setFields(List<FieldBase> fields) {
        this.fields = fields;
    }

    public List<MethodBase> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodBase> methods) {
        this.methods = methods;
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

    public ExtendsBase getExtend() {
        return extend;
    }

    public void setExtend(ExtendsBase extend) {
        this.extend = extend;
    }

    public List<ImplementsBase> getImpls() {
        return impls;
    }

    public void setImpls(List<ImplementsBase> impls) {
        this.impls = impls;
    }

    private String wrapPackage() {
        return "package " + this.packageInfo + ";\n\n";
    }

    private void processImports() {
        if (!CollectionUtils.isEmpty(this.annotations)) {
            for (AnnotationBase a : this.annotations) {
                addImport(a);
                if (!CollectionUtils.isEmpty(a.getFields())) {
                    for (AnnotationField b : a.getFields()) {
                        addImport(b);
                    }
                }
            }
        }
        if (this.extend != null) {
            addImport(this.extend);
        }
        if (!CollectionUtils.isEmpty(this.impls)) {
            for (ImplementsBase i : this.impls) {
                addImport(i);
            }
        }
        if (!CollectionUtils.isEmpty(this.fields)) {
            for (FieldBase f : this.fields) {
                addImport(f);
                handleAnnotationImport(f.getAnnotations());
            }
        }
        if (!CollectionUtils.isEmpty(this.methods)) {
            for (MethodBase m : this.methods) {
                addImport(m);
                addImport(m.getTemplate());
                handleAnnotationImport(m.getAnnotations());
            }
        }
    }

    private void handleAnnotationImport(List<AnnotationBase> annotations) {
        if (!CollectionUtils.isEmpty(annotations)) {
            for (AnnotationBase a : annotations) {
                addImport(a);
                if (!CollectionUtils.isEmpty(a.getFields())) {
                    for (AnnotationField b : a.getFields()) {
                        addImport(b);
                    }
                }
            }
        }
    }

    private String wrapImport() {
        processImports();
        StringBuilder s = new StringBuilder();
        for (String i: this.imports) {
            s.append("import ").append(i).append(";\n");
        }
        s.append("\n");
        return s.toString();
    }

    private String wrapAnnotation() {
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(this.annotations)) {
            sb.append(buildAnnotation(this.annotations, "\n"));
        }
        return sb.toString();
    }

    private String wrapJavadoc() {
        String lineFeed = "\n * ";
        return buildJavadoc(this.doc, lineFeed);
    }

    private String wrapClass() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.declaration).append(" ").append(this.type).append(" ").append(this.name).append(" ");
        if (this.extend != null) {
            sb.append("extends ").append(this.extend.getName()).append(" ");
        }
        if (this.impls != null) {
            sb.append("implements ");
            for (ImplementsBase i : this.impls) {
                sb.append(i.getName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" {\n");
        return sb.toString();
    }

    private String wrapField() {
        String lineFeed = "\n    ";
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(this.fields)) {
            for (FieldBase f : this.fields) {
                sb.append(lineFeed);
                if (f.getDoc() != null) {
                    sb.append(buildJavadoc(f.getDoc(), lineFeed + " * "));
                }
                if (!CollectionUtils.isEmpty(f.getAnnotations())) {
                    sb.append(buildAnnotation(f.getAnnotations(), "\n    "));
                }
                sb.append(f.getDeclaration()).append(" ").append(f.getType()).append(" ").append(f.getName());
                if (f.getValue() != null) {
                    sb.append(" = ");
                    if (StringUtils.equals(f.getType(), BizFieldType.STRING.value())) {
                        sb.append("\"").append(f.getValue()).append("\"").append(";\n");
                    } else {
                        sb.append(f.getValue()).append(";\n");
                    }
                } else {
                    sb.append(";\n");
                }
            }
        }
        return sb.toString();
    }

    private String wrapMethod() {
        String lineFeed = "\n    ";
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(this.methods)) {
            for (MethodBase m : this.methods) {
                sb.append(lineFeed).append(buildJavadoc(m.getDoc(), lineFeed));
                if (!CollectionUtils.isEmpty(m.getAnnotations())) {
                    sb.append(buildAnnotation(m.getAnnotations(), lineFeed));
                }
                sb.append(m.getTemplate().getText()).append(lineFeed);
            }
        }
        sb.append("\n");
        return sb.toString();
    }


    private String buildJavadoc(JavadocBase doc, String lineFeed) {
        StringBuilder sb = new StringBuilder();
        if (doc != null) {
            sb.append("/**").append(lineFeed);
            if (doc.getDesc() != null) {
                sb.append(doc.getDesc()).append(lineFeed);
            }
            if (doc.getContents() != null) {
                sb.append(lineFeed);
                for (Content content: doc.getContents()) {
                    sb.append("@").append(content.getKey()).append(" ").append(content.getValue())
                            .append(lineFeed);
                }
            }
            String substring = lineFeed.substring(0, lineFeed.lastIndexOf("*") - 1);
            sb.delete(sb.length()-1, sb.length()).append("/").append(substring);
        }
        return sb.toString();
    }

    private String buildAnnotation(List<AnnotationBase> annos, String lineFeed) {
        StringBuilder sb = new StringBuilder();
        for (AnnotationBase a : annos) {
            sb.append("@").append(a.getName());
            if (a.getValue() != null) {
                if (a.getStringType()) {
                    sb.append("(\"").append(a.getValue()).append("\")");
                } else {
                    sb.append("(").append(a.getValue()).append(")");
                }
            } else if (!CollectionUtils.isEmpty(a.getFields())) {
                sb.append("(");
                for (AnnotationField f : a.getFields()) {
                    sb.append(f.getKey());
                    if (!f.getStringType()) {
                        sb.append(" = ").append(f.getValue()).append(", ");
                    } else {
                        sb.append(" = ").append("\"").append(f.getValue()).append("\", ");
                    }
                }
                sb.delete(sb.length() - 2, sb.length()).append(")");
            }
            sb.append(lineFeed);
        }
        return sb.toString();
    }

    private void addImport(ImportBase t) {
        if (t.getImportClassName() != null) {
            this.imports.addAll(Arrays.asList(t.getImportClassName()));
        }
    }

}
