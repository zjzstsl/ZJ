package org.tis.tools.asf.module.biz.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.asf.module.biz.entity.BizApp;

/**
 * describe: 
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
public interface IBizAppService extends IService<BizApp> {

    void importErApp(String erAppId);

    BizApp queryBizAppDetailById(String bizApp);


}
