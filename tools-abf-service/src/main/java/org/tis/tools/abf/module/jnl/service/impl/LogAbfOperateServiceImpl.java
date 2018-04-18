package org.tis.tools.abf.module.jnl.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.core.exception.i18.ExceptionCodes;
import org.tis.tools.abf.module.common.log.vo.LogHistoryDetail;
import org.tis.tools.abf.module.common.log.vo.LogOperateDetail;
import org.tis.tools.abf.module.jnl.dao.LogAbfOperateMapper;
import org.tis.tools.abf.module.jnl.entity.LogAbfChange;
import org.tis.tools.abf.module.jnl.entity.LogAbfHistory;
import org.tis.tools.abf.module.jnl.entity.LogAbfKeyword;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;
import org.tis.tools.abf.module.jnl.exception.JnlManagementException;
import org.tis.tools.abf.module.jnl.service.ILogAbfChangeService;
import org.tis.tools.abf.module.jnl.service.ILogAbfHistoryService;
import org.tis.tools.abf.module.jnl.service.ILogAbfKeywordService;
import org.tis.tools.abf.module.jnl.service.ILogAbfOperateService;

import java.util.Date;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * describe:
 *
 * @author zhaoch
 * @date 2018/3/29
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAbfOperateServiceImpl extends ServiceImpl<LogAbfOperateMapper, LogAbfOperate>
        implements ILogAbfOperateService{

    @Autowired
    private ILogAbfKeywordService logAbfKeywordService;
    @Autowired
    private ILogAbfChangeService logAbfChangeService;
    @Autowired
    private ILogAbfHistoryService logAbfHistoryService;

    @Override
    public void createOperatorLog(LogOperateDetail log) throws JnlManagementException {
        if (null == log || null == log.getInstance()) {
            throw new JnlManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT, wrap("", "LOG_OPERATE"));
        }
        LogAbfOperate logAbfOperate = log.getInstance();
        if (StringUtils.isBlank(logAbfOperate.getOperateFrom())) {
            throw new JnlManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,
                    wrap(LogAbfOperate.COLUMN_OPERATE_FROM, LogAbfOperate.TABLE_NAME));
        }
        if (logAbfOperate.getOperateResult() == null) {
            throw new JnlManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,
                    wrap(LogAbfOperate.COLUMN_OPERATE_RESULT, LogAbfOperate.TABLE_NAME));
        }
        logAbfOperate.setOperateTime(new Date());
        insert(logAbfOperate);
        for (LogHistoryDetail obj : log.getAllObj()) {
            LogAbfHistory logAbfHistory = obj.getInstance();
            logAbfHistory.setGuidOperate(logAbfOperate.getGuid());
            logAbfHistoryService.insert(logAbfHistory);
            for (LogAbfKeyword logAbfKeyword : obj.getKeywords()) {
                logAbfKeyword.setGuidHistory(logAbfHistory.getGuid());
                logAbfKeywordService.insert(logAbfKeyword);
            }
            for (LogAbfChange logAbfChange: obj.getChanges()) {
                logAbfChange.setGuidHistory(logAbfHistory.getGuid());
                logAbfChangeService.insert(logAbfChange);
            }
        }
    }
}
