package org.tis.tools.asf.module.generate.engine.metadata.java.doc;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JavadocBase {

    private String desc;

    private List<Content> contents;

    public JavadocBase() {}

    public JavadocBase(String desc) {
        this.desc = desc;
    }

    public JavadocBase(String desc, List<Content> contents) {
        this.desc = desc;
        this.contents = contents;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public static JavadocBase createClassDoc(String desc) {
        JavadocBase d = new JavadocBase();
        d.setDesc(desc);
        ArrayList<Content> contents = new ArrayList<>();
        contents.add(new Content("author", "Auto Generate Tools"));
        contents.add(new Content("date", new SimpleDateFormat("yyyy/MM/dd").format(new Date())));
        d.setContents(contents);
        return d;
    }
}
