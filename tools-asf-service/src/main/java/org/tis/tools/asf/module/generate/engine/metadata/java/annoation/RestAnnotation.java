package org.tis.tools.asf.module.generate.engine.metadata.java.annoation;


/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/11
 **/
public class RestAnnotation extends AnnotationBase {

    public static RestAnnotation restController() {
        RestAnnotation r = new RestAnnotation();
        r.setName("RestController");
        r.setImportClassName(new String[]{"org.springframework.web.bind.annotation.*"});
        return r;
    }

    public static RestAnnotation requestMapping(String mappingUrl) {
        RestAnnotation r = new RestAnnotation();
        r.setName("RequestMapping");
        r.setValue(mappingUrl);
        r.setImportClassName(new String[]{"org.springframework.web.bind.annotation.*"});
        return r;
    }

    public static RestAnnotation postMapping(String mappingUrl) {
        RestAnnotation r = new RestAnnotation();
        r.setName("PostMapping");
        r.setValue(mappingUrl);
        r.setImportClassName(new String[]{"org.springframework.web.bind.annotation.*"});
        return r;
    }

    public static RestAnnotation putMapping() {
        RestAnnotation r = new RestAnnotation();
        r.setName("PutMapping");
        r.setImportClassName(new String[]{"org.springframework.web.bind.annotation.*"});
        return r;
    }

    public static RestAnnotation deleteMapping(String mappingUrl) {
        RestAnnotation r = new RestAnnotation();
        r.setName("DeleteMapping");
        r.setValue(mappingUrl);
        r.setImportClassName(new String[]{"org.springframework.web.bind.annotation.*"});
        return r;
    }

    public static RestAnnotation getMapping(String mappingUrl) {
        RestAnnotation r = new RestAnnotation();
        r.setName("GetMapping");
        r.setValue(mappingUrl);
        r.setImportClassName(new String[]{"org.springframework.web.bind.annotation.*"});
        return r;
    }
}
