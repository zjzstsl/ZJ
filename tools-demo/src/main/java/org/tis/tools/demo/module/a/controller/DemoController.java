package org.tis.tools.demo.module.a.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.demo.module.a.entity.Demo;
import org.tis.tools.demo.module.a.service.IDemoService;


/**
 * 演示例子
 *
 * @author Shiyunlai
 */
@Api(tags = "Demo管理")
@RestController
@RequestMapping("/demo")
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    //    @Resource
    @Autowired
    @Qualifier("demoServiceImpl")
    private IDemoService demoService;

    @ApiOperation("hello演示接口")
    @PostMapping("/hello")
    public Object hello(@ApiParam("json形式的Demo对象信息") @RequestBody Demo demo) {

        logger.info(demo.toString());
        return demo;
    }

    @ApiOperation("列出Demo树节点")
    @GetMapping("/tree")
    public Object tree() {
        return demoService.tree();
    }

    @ApiOperation("列出（历史）Demo树节点")
    @GetMapping("/treehis")
    public Object treeHis() {
        return demoService.treeHis();
    }

    @ApiOperation("增加Demo树节点")
    @PostMapping("")
    public void addDemoTree(@RequestBody Demo demo) {
        // 新增记录（指定数据源）
        demoService.add(demo);
    }

    @ApiOperation("增加（历史）Demo树节点")
    @PostMapping("/his")
    public void addDemoTreeHis(@RequestBody Demo demo){
        demoService.addHis(demo);
    }

    @ApiOperation("修改Demo节点")
    @PutMapping("")
    public void updateDemo( @RequestBody Demo demo){
        demoService.updateById(demo);
    }
}
