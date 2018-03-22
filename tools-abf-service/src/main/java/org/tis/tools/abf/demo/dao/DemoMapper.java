package org.tis.tools.abf.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.tis.tools.abf.demo.entity.Demo;
import org.tis.tools.abf.demo.vo.DemoTreeVo;

import java.util.List;

/**
 * Demo
 * <p>
 * XXXX Mapper接口
 * <p>
 * 开发规范：
 * 1.继承mybatisplus的BaseMapper
 * 2.列出通用mapper之外的方法
 *
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
public interface DemoMapper extends BaseMapper<Demo> {

    /**
     * 列出tree结构
     *
     * @return
     */
    List<DemoTreeVo> tree();
}
