package org.tis.tools.abf.module.om.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.tis.tools.abf.module.om.dao.OmEmployeeMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * omEmployee的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmployeeServiceImpl extends ServiceImpl<OmEmployeeMapper, OmEmployee> implements IOmEmployeeService {

}

