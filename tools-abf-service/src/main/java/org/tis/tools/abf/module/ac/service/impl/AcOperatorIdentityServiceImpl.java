package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.dao.AcOperatorIdentityMapper;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentity;
import org.springframework.transaction.annotation.Transactional;

/**
 * acOperatorIdentity的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorIdentityServiceImpl extends ServiceImpl<AcOperatorIdentityMapper, AcOperatorIdentity> implements IAcOperatorIdentityService {

}

