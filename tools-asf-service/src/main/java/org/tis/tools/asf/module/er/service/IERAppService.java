package org.tis.tools.asf.module.er.service;

import com.baomidou.mybatisplus.service.IService;
import org.springframework.stereotype.Service;
import org.tis.tools.asf.module.er.entity.ERApp;


public interface IERAppService extends IService<ERApp> {

    ERApp queryERAppDetailById(String erAppId);

    void parseERM(String appName, String xmlStr);
}
