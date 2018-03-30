package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.jnl.dao.LogAbfHistoryMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfHistory;
import org.tis.tools.abf.module.jnl.service.ILogAbfHistoryService;

/**
 * describe:
 *
 * @author zhaoch
 * @date 2018/3/29
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAbfHistoryServiceImpl extends ServiceImpl<LogAbfHistoryMapper, LogAbfHistory>
        implements ILogAbfHistoryService {
}
