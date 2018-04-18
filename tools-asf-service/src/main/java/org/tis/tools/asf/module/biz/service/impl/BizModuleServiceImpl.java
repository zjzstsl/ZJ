package org.tis.tools.asf.module.biz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.asf.module.biz.dao.BizModuleMapper;
import org.tis.tools.asf.module.biz.entity.BizModule;
import org.tis.tools.asf.module.biz.service.IBizModuleService;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BizModuleServiceImpl extends ServiceImpl<BizModuleMapper, BizModule> implements IBizModuleService {
}
