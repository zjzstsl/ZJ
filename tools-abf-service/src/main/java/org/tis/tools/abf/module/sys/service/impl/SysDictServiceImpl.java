package org.tis.tools.abf.module.sys.service.impl;

import org.tis.tools.abf.module.sys.dao.SysDictMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.springframework.transaction.annotation.Transactional;

/**
 * sysDict的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

}

