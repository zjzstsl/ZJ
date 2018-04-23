package org.tis.tools.abf.module.sys.service.impl;

import org.tis.tools.abf.module.sys.service.ISysRunConfigService;
import org.tis.tools.abf.module.sys.dao.SysRunConfigMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.entity.SysRunConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * sysRunConfig的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRunConfigServiceImpl extends ServiceImpl<SysRunConfigMapper, SysRunConfig> implements ISysRunConfigService {

}

