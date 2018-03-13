package org.tis.toolsfortest.usestarter.persistence.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.tis.toolsfortest.usestarter.persistence.demo.entity.Demo;
import org.tis.toolsfortest.usestarter.persistence.demo.service.IDemoService;

import javax.annotation.Resource;

/**
 * 演示例子
 *
 * @author Shiyunlai
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

//    @Autowired
//    @Qualifier("demoServiceImpl")
    @Resource
    private IDemoService demoService;

    @PostMapping("/hello")
    public Object hello(@RequestBody Demo demo) {

        logger.info(demo.toString());
        return demo;
    }

    @GetMapping("/tree")
    public Object tree() {
        return demoService.tree();
    }

    @GetMapping("/treehis")
    public Object treeHis() {
        return demoService.treeHis();
    }

    @PostMapping("")
    public void addDemoTree(@RequestBody Demo demo) {
        // 新增记录（指定数据源）
        demoService.add(demo);
    }

    /**
     * 记录历史记录
     * @param demo
     */
    @PostMapping("/his")
    public void addDemoTreeHis(@RequestBody Demo demo){
        demoService.addHis(demo);
    }

    @PutMapping("")
    public void updateDemo( @RequestBody Demo demo){
        demoService.updateById(demo);
    }
}