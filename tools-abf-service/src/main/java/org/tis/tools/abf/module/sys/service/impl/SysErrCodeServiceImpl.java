package org.tis.tools.abf.module.sys.service.impl;

import org.tis.tools.abf.module.sys.entity.SysErrCode;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.dao.SysErrCodeMapper;
import org.tis.tools.abf.module.sys.service.ISysErrCodeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * sysErrCode的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysErrCodeServiceImpl extends ServiceImpl<SysErrCodeMapper, SysErrCode> implements ISysErrCodeService {

}

