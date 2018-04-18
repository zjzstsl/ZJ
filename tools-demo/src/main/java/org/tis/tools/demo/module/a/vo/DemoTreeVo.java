package org.tis.tools.demo.module.a.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Demo 实体对象的树节点数据结构
 * 假设在Tred上展示时，只需要提供guid和名称
 *
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
@Data
@ToString
public class DemoTreeVo implements Serializable {
    private static final long serialVersionUID = 1L;
    String guid;
    String name;
    String age ;
}
