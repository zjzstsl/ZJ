package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcAppConfigMapper;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * acAppConfig的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcAppConfigServiceImpl extends ServiceImpl<AcAppConfigMapper, AcAppConfig> implements IAcAppConfigService {

}

