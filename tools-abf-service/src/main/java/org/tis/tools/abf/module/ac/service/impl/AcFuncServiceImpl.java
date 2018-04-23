package org.tis.tools.abf.module.ac.service.impl;

import org.springframework.stereotype.Service;
import org.tis.tools.abf.module.ac.dao.AcFuncMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.springframework.transaction.annotation.Transactional;

/**
 * acFunc的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcFuncServiceImpl extends ServiceImpl<AcFuncMapper, AcFunc> implements IAcFuncService {

}

