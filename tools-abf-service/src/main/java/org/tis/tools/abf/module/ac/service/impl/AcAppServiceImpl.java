package org.tis.tools.abf.module.ac.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcAppMapper;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.springframework.transaction.annotation.Transactional;

/**
 * acApp的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcAppServiceImpl extends ServiceImpl<AcAppMapper, AcApp> implements IAcAppService {

}

