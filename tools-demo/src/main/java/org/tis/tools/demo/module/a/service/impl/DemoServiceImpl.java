package org.tis.tools.demo.module.a.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tis.tools.demo.module.a.dao.DemoMapper;
import org.tis.tools.demo.module.a.entity.Demo;
import org.tis.tools.demo.module.a.service.IDemoService;
import org.tis.tools.demo.module.a.vo.DemoTreeVo;
import org.tis.tools.starter.multidatasource.annotion.DataSource;

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
    public List<DemoTreeVo> tree() {
        return demoMapper.tree();
    }

    @Override
    public void add(Demo demo) {
        demoMapper.insertAllColumn(demo);
    }

    /**
     * 从另外一个数据源获取历史节点信息
     *
     * @return
     */
    @Override
    @DataSource(name = "otherDataSource")
    public List<DemoTreeVo> treeHis() {
        return demoMapper.tree();
    }

    @Override
    @DataSource(name = "otherDataSource")
    public void addHis(Demo demo) {
        demoMapper.insertAllColumn(demo);
    }
}
