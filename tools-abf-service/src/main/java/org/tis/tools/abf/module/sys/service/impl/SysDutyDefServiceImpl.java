package org.tis.tools.abf.module.sys.service.impl;

import org.springframework.stereotype.Service;
import org.tis.tools.abf.module.sys.dao.SysDutyDefMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.service.ISysDutyDefService;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.sys.entity.SysDutyDef;

/**
 * sysDutyDef的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDutyDefServiceImpl extends ServiceImpl<SysDutyDefMapper, SysDutyDef> implements ISysDutyDefService {

}

