package org.tis.tools.abf.module.om.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.service.IOmGroupService;
import org.tis.tools.abf.module.om.entity.OmGroup;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.om.dao.OmGroupMapper;

/**
 * omGroup的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmGroupServiceImpl extends ServiceImpl<OmGroupMapper, OmGroup> implements IOmGroupService {

}

