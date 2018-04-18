package org.tis.tools.asf.module.biz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.asf.module.biz.dao.BizFieldMapper;
import org.tis.tools.asf.module.biz.entity.BizField;
import org.tis.tools.asf.module.biz.service.IBizFieldService;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BizFieldServiceImpl extends ServiceImpl<BizFieldMapper, BizField> implements IBizFieldService {
}
