package org.tis.tools.asf.module.er.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.asf.module.er.dao.ERCategoryMapper;
import org.tis.tools.asf.module.er.entity.ERCategory;
import org.tis.tools.asf.module.er.service.IERCategoryService;

/**
 * describe:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ERCategoryServiceImpl extends ServiceImpl<ERCategoryMapper, ERCategory> implements IERCategoryService {
}
