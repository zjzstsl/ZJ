package org.tis.tools.abf.module.ac.service.impl;

import org.tis.tools.abf.module.ac.dao.AcOperatorMenuMapper;
import org.tis.tools.abf.module.ac.service.IAcOperatorMenuService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcOperatorMenu;
import org.springframework.transaction.annotation.Transactional;

/**
 * acOperatorMenu的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorMenuServiceImpl extends ServiceImpl<AcOperatorMenuMapper, AcOperatorMenu> implements IAcOperatorMenuService {

}

