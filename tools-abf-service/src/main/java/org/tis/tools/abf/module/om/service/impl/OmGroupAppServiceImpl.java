package org.tis.tools.abf.module.om.service.impl;

import org.tis.tools.abf.module.om.entity.OmGroupApp;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.dao.OmGroupAppMapper;
import org.tis.tools.abf.module.om.service.IOmGroupAppService;
import org.springframework.transaction.annotation.Transactional;

/**
 * omGroupApp的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmGroupAppServiceImpl extends ServiceImpl<OmGroupAppMapper, OmGroupApp> implements IOmGroupAppService {

}

