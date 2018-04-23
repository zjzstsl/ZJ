package org.tis.tools.abf.module.om.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.service.IOmEmpOrgService;
import org.tis.tools.abf.module.om.dao.OmEmpOrgMapper;
import org.tis.tools.abf.module.om.entity.OmEmpOrg;
import org.springframework.transaction.annotation.Transactional;

/**
 * omEmpOrg的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmpOrgServiceImpl extends ServiceImpl<OmEmpOrgMapper, OmEmpOrg> implements IOmEmpOrgService {

}

