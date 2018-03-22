package org.tis.tools.abf.demo.vo;

import java.io.Serializable;

/**
 * Demo 实体对象的树节点数据结构
 * 假设在Tred上展示时，只需要提供guid和名称
 *
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
public class DemoTreeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    String guid;
    String name;
    String age ;

    @Override
    public String toString() {
        return "DemoTreeVo{" +
                "guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
