package org.tis.tools.abf.module.om.service.impl;

import org.tis.tools.abf.module.om.dao.OmGroupPositionMapper;
import org.tis.tools.abf.module.om.service.IOmGroupPositionService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.entity.OmGroupPosition;
import org.springframework.transaction.annotation.Transactional;

/**
 * omGroupPosition的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmGroupPositionServiceImpl extends ServiceImpl<OmGroupPositionMapper, OmGroupPosition> implements IOmGroupPositionService {

}

