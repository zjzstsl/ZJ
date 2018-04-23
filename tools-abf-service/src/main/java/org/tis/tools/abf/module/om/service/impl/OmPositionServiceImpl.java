package org.tis.tools.abf.module.om.service.impl;

import org.tis.tools.abf.module.om.entity.OmPosition;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.service.IOmPositionService;
import org.tis.tools.abf.module.om.dao.OmPositionMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * omPosition的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmPositionServiceImpl extends ServiceImpl<OmPositionMapper, OmPosition> implements IOmPositionService {

}

