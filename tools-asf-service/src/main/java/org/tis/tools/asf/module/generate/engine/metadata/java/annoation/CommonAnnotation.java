package org.tis.tools.asf.module.generate.engine.metadata.java.annoation;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/11
 **/
public class CommonAnnotation extends AnnotationBase {

    public static AnnotationBase autowired() {
        return new AnnotationBase("Autowired",
                "org.springframework.beans.factory.annotation.Autowired");
    }
}
