package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.service.IAcMenuService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.tis.tools.abf.module.ac.dao.AcMenuMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * acMenu的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcMenuServiceImpl extends ServiceImpl<AcMenuMapper, AcMenu> implements IAcMenuService {

}

