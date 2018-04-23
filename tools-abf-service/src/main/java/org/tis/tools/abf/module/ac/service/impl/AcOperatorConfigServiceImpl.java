package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.service.IAcOperatorConfigService;
import org.tis.tools.abf.module.ac.entity.AcOperatorConfig;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcOperatorConfigMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * acOperatorConfig的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorConfigServiceImpl extends ServiceImpl<AcOperatorConfigMapper, AcOperatorConfig> implements IAcOperatorConfigService {

}

