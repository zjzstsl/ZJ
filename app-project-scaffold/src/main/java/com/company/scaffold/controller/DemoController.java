package com.company.scaffold.controller;

import com.company.scaffold.entity.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @PostMapping("/hello")
    public Object hello(@RequestBody Demo demo){

        logger.info( demo.toString() );
        return demo ;
    }
}
