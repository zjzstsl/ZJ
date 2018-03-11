package com.company.scaffold.demo.controller;

import com.company.scaffold.demo.entity.Demo;
import com.company.scaffold.demo.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * 演示例子
 *
 * @author Shiyunlai
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    @Qualifier("demoServiceImpl")
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
        demoService.insert(demo);
    }

    @PutMapping("")
    public void updateDemo( @RequestBody Demo demo){
        demoService.updateById(demo);
    }
}
