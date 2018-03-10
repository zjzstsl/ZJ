package com.company.scaffold.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.company.scaffold.base.constant.DatasourceEnum;
import com.company.scaffold.core.multidatasource.annotion.DataSource;
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

    /**
     * 从默认数据源中获取Demo节点信息
     *
     * @return
     */
    @Override
    @DataSource //使用默认数据源， 或 @DataSource(name="default")；不加这个注释，使用默认数据源
    public List<DemoTreeVo> tree() {
        return demoMapper.tree();
    }

    /**
     * 从另外一个数据源获取历史节点信息
     *
     * @return
     */
    @Override
    @DataSource(name = "otherDataSource") //使用指定的数据源
    public List<DemoTreeVo> treeHis() {
        return demoMapper.tree();
    }
}
