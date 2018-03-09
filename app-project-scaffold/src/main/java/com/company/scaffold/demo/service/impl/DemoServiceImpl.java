package com.company.scaffold.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.company.scaffold.demo.dao.DemoMapper;
import com.company.scaffold.demo.entity.Demo;
import com.company.scaffold.demo.service.IDemoService;
import com.company.scaffold.demo.vo.DemoTreeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo 的业务逻辑实现类
 *
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

    @Resource
    DemoMapper demoMapper;

    @Override
    public List<DemoTreeVo> tree() {
        return demoMapper.tree();
    }
}
