package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.service.IAcRoleEntityService;
import org.tis.tools.abf.module.ac.dao.AcRoleEntityMapper;
import org.tis.tools.abf.module.ac.entity.AcRoleEntity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * acRoleEntity的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcRoleEntityServiceImpl extends ServiceImpl<AcRoleEntityMapper, AcRoleEntity> implements IAcRoleEntityService {

}

