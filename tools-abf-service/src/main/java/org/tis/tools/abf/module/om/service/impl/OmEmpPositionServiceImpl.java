package org.tis.tools.abf.module.om.service.impl;

import org.springframework.stereotype.Service;
import org.tis.tools.abf.module.om.dao.OmEmpPositionMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.entity.OmEmpPosition;
import org.tis.tools.abf.module.om.service.IOmEmpPositionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * omEmpPosition的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmpPositionServiceImpl extends ServiceImpl<OmEmpPositionMapper, OmEmpPosition> implements IOmEmpPositionService {

}

