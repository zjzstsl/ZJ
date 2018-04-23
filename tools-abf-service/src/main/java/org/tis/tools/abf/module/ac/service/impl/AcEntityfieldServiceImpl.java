package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.service.IAcEntityfieldService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcEntityfieldMapper;
import org.tis.tools.abf.module.ac.entity.AcEntityfield;
import org.springframework.transaction.annotation.Transactional;

/**
 * acEntityfield的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcEntityfieldServiceImpl extends ServiceImpl<AcEntityfieldMapper, AcEntityfield> implements IAcEntityfieldService {

}

