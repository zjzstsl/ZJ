package org.tis.tools.asf.module.generate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.tis.tools.asf.module.generate.engine.metadata.java.JavaBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.annoation.AnnotationField;
import org.tis.tools.asf.module.generate.engine.metadata.java.doc.JavadocBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.field.FieldBase;
import org.tis.tools.asf.module.generate.engine.metadata.java.implement.ImplementsBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {

    private static String ABSOLUTE_PATH = new File("").getAbsolutePath();



    public static void main(String[] args) throws IOException {

        // JAVA文件基类
        JavaBase emp = new JavaBase();
        // 设置package
        emp.setPackageInfo("org.tis.tools.module.om.entity");
//        emp.setImports();

        // 类注解
        List<AnnotationBase> classAnnos = new ArrayList<>();
        AnnotationBase dataAnno = new AnnotationBase("Data","lombok.Data");
        classAnnos.add(dataAnno);
        AnnotationBase tableAnno = new AnnotationBase("TableName", "om_employee",
                "com.baomidou.mybatisplus.annotations.TableName");
        classAnnos.add(tableAnno);
        emp.setAnnotations(classAnnos);
        // 类声明
        emp.setDeclaration("public");
        // 类型
        emp.setType("class");
        // 类名
        emp.setName("Employee");
        // 类实现
        emp.setImpls(Collections.singletonList(new ImplementsBase("Serializable", "java.io.Serializable")));
        // 类属性 SerialVersionUID
        List<FieldBase> fieldList = new ArrayList<>();
        emp.setFields(fieldList);
        fieldList.add(FieldBase.genSerialVersionUID());
        // 类属性 guid
        FieldBase guid = new FieldBase();
        fieldList.add(guid);
        guid.setDeclaration("private");
        guid.setType("String");
        guid.setName("guid");
        guid.setDoc(new JavadocBase("全局唯一ID"));
        List<AnnotationBase> guidAnnos = new ArrayList<>();
        guid.setAnnotations(guidAnnos);
        // 属性注解 tableId
        List<AnnotationField> af1 = new ArrayList<>();
        af1.add(new AnnotationField("value", "guid"));
        af1.add(new AnnotationField("type", "IdType.ID_WORKER_STR", false));
        AnnotationBase annotationBase = new AnnotationBase("TableId", af1,
                "com.baomidou.mybatisplus.annotations.TableId");
        guidAnnos.add(annotationBase);
        // 属性注解 Null
        List<AnnotationField> af2 = new ArrayList<>();
        af2.add(new AnnotationField("message", "主键由系统自动生成"));
        af2.add(new AnnotationField("groups", "{AddValidateGroup.class}", false,
                "org.tis.tools.validation.AddValidateGroup"));
        AnnotationBase annotationBase2 = new AnnotationBase("TableId", af2,
                "com.baomidou.mybatisplus.annotations.TableId");
        guidAnnos.add(annotationBase2);
        // 属性注解 NotBlank
        List<AnnotationField> af3 = new ArrayList<>();
        af3.add(new AnnotationField("message", "修改时主键不能为空"));
        af3.add(new AnnotationField("groups", "{UpdateValidateGroup.class}", false,
                "org.tis.tools.validation.UpdateValidateGroup"));
        AnnotationBase annotationBase3 = new AnnotationBase("TableId", af3,
                "com.baomidou.mybatisplus.annotations.TableId");
        guidAnnos.add(annotationBase3);

//        System.out.println(s);

        System.out.println(JSON.toJSONString(emp, SerializerFeature.PrettyFormat));

//        String s = FileUtils.readFileToString(new File(ABSOLUTE_PATH + "/src/main/resources/template" +
//                File.separator + "generate" + File.separator + "Entity.java.btl"), "UTF-8");
//        System.out.println(s);

    }


}
