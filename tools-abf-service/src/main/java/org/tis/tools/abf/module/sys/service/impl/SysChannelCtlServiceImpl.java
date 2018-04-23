package org.tis.tools.abf.module.sys.service.impl;

import org.tis.tools.abf.module.sys.dao.SysChannelCtlMapper;
import org.tis.tools.abf.module.sys.service.ISysChannelCtlService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.entity.SysChannelCtl;
import org.springframework.transaction.annotation.Transactional;

/**
 * sysChannelCtl的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysChannelCtlServiceImpl extends ServiceImpl<SysChannelCtlMapper, SysChannelCtl> implements ISysChannelCtlService {

}

