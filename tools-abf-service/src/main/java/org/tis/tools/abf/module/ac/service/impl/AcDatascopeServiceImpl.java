package org.tis.tools.abf.module.ac.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcDatascopeMapper;
import org.tis.tools.abf.module.ac.entity.AcDatascope;
import org.tis.tools.abf.module.ac.service.IAcDatascopeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * acDatascope的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcDatascopeServiceImpl extends ServiceImpl<AcDatascopeMapper, AcDatascope> implements IAcDatascopeService {

}

