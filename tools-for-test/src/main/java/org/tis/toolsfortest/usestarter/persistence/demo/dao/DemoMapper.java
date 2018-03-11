package org.tis.toolsfortest.usestarter.persistence.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.tis.toolsfortest.usestarter.persistence.demo.entity.Demo;
import org.tis.toolsfortest.usestarter.persistence.demo.vo.DemoTreeVo;

import java.util.List;

/**
 * Demo Mapper接口
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
public interface DemoMapper extends BaseMapper<Demo> {

    public List<DemoTreeVo> tree() ;
}
