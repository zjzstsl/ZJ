package com.company.scaffold;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 *
 * @author Shiyunlai
 */
@SpringBootApplication
public class ScaffoldApplication {

    private final static Logger logger = LoggerFactory.getLogger(ScaffoldApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
        logger.info("application start success!");
    }
}
