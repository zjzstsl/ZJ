package org.tis.tools.asf.module.biz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.asf.module.biz.dao.BizModelMapper;
import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.biz.service.IBizModelService;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BizModelServiceImpl extends ServiceImpl<BizModelMapper, BizModel> implements IBizModelService {
}
