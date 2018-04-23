package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.service.IAcRoleDatascopeService;
import org.tis.tools.abf.module.ac.entity.AcRoleDatascope;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcRoleDatascopeMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * acRoleDatascope的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcRoleDatascopeServiceImpl extends ServiceImpl<AcRoleDatascopeMapper, AcRoleDatascope> implements IAcRoleDatascopeService {

}

