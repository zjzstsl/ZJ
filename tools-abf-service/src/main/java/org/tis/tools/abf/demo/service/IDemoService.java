package org.tis.tools.abf.demo.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.demo.entity.Demo;
import org.tis.tools.abf.demo.vo.DemoTreeVo;

import java.util.List;

/**
 * Demo
 * <p>
 * XXX 的业务逻辑接口
 * <p>
 * 开发规范：
 * 1.mybatis plus的IService
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
