package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.dao.AcOperatorFuncMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.service.IAcOperatorFuncService;
import org.tis.tools.abf.module.ac.entity.AcOperatorFunc;
import org.springframework.transaction.annotation.Transactional;

/**
 * acOperatorFunc的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorFuncServiceImpl extends ServiceImpl<AcOperatorFuncMapper, AcOperatorFunc> implements IAcOperatorFuncService {

}

