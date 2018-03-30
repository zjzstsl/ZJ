package org.tis.tools.abf.module.sys.service;


import org.tis.tools.abf.module.sys.exception.SysManagementException;

public interface ISeqnoService {

    /**
     * 获取下一个序列号
     * @param seqKey 序号键值
     * @return
     * @throws SysManagementException
     */
    long getNextSequence(String seqKey, String seqName) throws SysManagementException;

}
