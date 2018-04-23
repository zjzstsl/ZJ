package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.entity.AcRoleEntityfield;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.service.IAcRoleEntityfieldService;
import org.tis.tools.abf.module.ac.dao.AcRoleEntityfieldMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * acRoleEntityfield的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcRoleEntityfieldServiceImpl extends ServiceImpl<AcRoleEntityfieldMapper, AcRoleEntityfield> implements IAcRoleEntityfieldService {

}

