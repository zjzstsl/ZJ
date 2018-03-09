package com.company.scaffold.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.company.scaffold.demo.entity.Demo;
import com.company.scaffold.demo.vo.DemoTreeVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Demo Mapper接口
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
public interface DemoMapper extends BaseMapper<Demo> {

    public List<DemoTreeVo> tree() ;
}
