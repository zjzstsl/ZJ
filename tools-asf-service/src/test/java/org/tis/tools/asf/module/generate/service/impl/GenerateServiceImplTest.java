package org.tis.tools.asf.module.generate.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.asf.base.BaseTest;
import org.tis.tools.asf.module.generate.service.IGenerateService;

public class GenerateServiceImplTest extends BaseTest {

    @Autowired
    private IGenerateService generateService;

    @Test
    public void generate() {
        // 3.BIZ模型生成文件
        String basePackage = "org.tis.tools.abf";
        String bizAppId = "986129701377404930";
        generateService.generate(bizAppId, basePackage);
    }
}