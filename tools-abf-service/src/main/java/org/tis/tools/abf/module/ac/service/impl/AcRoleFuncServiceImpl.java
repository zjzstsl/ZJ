package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcRoleFuncMapper;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.springframework.transaction.annotation.Transactional;

/**
 * acRoleFunc的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcRoleFuncServiceImpl extends ServiceImpl<AcRoleFuncMapper, AcRoleFunc> implements IAcRoleFuncService {

}

