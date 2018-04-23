package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.dao.AcOperatorRoleMapper;
import org.tis.tools.abf.module.ac.service.IAcOperatorRoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcOperatorRole;
import org.springframework.transaction.annotation.Transactional;

/**
 * acOperatorRole的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorRoleServiceImpl extends ServiceImpl<AcOperatorRoleMapper, AcOperatorRole> implements IAcOperatorRoleService {

}

