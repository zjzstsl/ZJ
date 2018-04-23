package org.tis.tools.abf.module.ac.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityresService;
import org.tis.tools.abf.module.ac.dao.AcOperatorIdentityresMapper;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentityres;
import org.springframework.transaction.annotation.Transactional;

/**
 * acOperatorIdentityres的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorIdentityresServiceImpl extends ServiceImpl<AcOperatorIdentityresMapper, AcOperatorIdentityres> implements IAcOperatorIdentityresService {

}

