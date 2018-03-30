package org.tis.tools.abf.module.jnl.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.common.log.vo.LogOperateDetail;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;
import org.tis.tools.abf.module.jnl.exception.JnlManagementException;

/**
 * describe: 
 *
 * @author zhaoch
 * @date 2018/3/30
 **/
public interface ILogAbfOperateService extends IService<LogAbfOperate> {

    /**
     * 新增操作日志
     * @param log 日志VO类
     * @throws JnlManagementException
     */
    void createOperatorLog(LogOperateDetail log) throws JnlManagementException;
}
