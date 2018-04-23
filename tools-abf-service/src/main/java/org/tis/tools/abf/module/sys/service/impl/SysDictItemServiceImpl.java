package org.tis.tools.abf.module.sys.service.impl;

import org.tis.tools.abf.module.sys.service.ISysDictItemService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.entity.SysDictItem;
import org.tis.tools.abf.module.sys.dao.SysDictItemMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * sysDictItem的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements ISysDictItemService {

}

