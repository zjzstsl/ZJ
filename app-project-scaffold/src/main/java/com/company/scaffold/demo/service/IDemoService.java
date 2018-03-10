package com.company.scaffold.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.company.scaffold.demo.entity.Demo;
import com.company.scaffold.demo.vo.DemoTreeVo;

import java.util.List;

/**
 * Demo 的业务逻辑接口
 *
 * @author Tools Modeler
 * @since 2018-03-01 12:12:34 123
 */
public interface IDemoService extends IService<Demo> {

    /**
     * 获取Demo数据的树节点信息
     *
     * @return
     */
    List<DemoTreeVo> tree();

    /**
     * 获取历史数据节点信息
     *
     * @return
     */
    List<DemoTreeVo> treeHis();
}
