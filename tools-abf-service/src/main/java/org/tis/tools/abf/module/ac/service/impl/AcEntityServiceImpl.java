package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.entity.AcEntity;
import org.tis.tools.abf.module.ac.dao.AcEntityMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.service.IAcEntityService;
import org.springframework.transaction.annotation.Transactional;

/**
 * acEntity的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcEntityServiceImpl extends ServiceImpl<AcEntityMapper, AcEntity> implements IAcEntityService {

}

