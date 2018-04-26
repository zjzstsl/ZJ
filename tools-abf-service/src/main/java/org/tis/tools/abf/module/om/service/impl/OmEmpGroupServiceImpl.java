package org.tis.tools.abf.module.om.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.om.service.IOmEmpGroupService;
import org.tis.tools.abf.module.om.dao.OmEmpGroupMapper;
import org.tis.tools.abf.module.om.entity.OmEmpGroup;
import org.springframework.transaction.annotation.Transactional;

/**
 * omEmpGroup的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmEmpGroupServiceImpl extends ServiceImpl<OmEmpGroupMapper, OmEmpGroup> implements IOmEmpGroupService {

    public OmEmpGroup createChildOrg(String areaCode, String orgDegree, String orgName, String orgType, String guidParents)
			throws OrgManagementException {

        OmEmpGroup org = new OmEmpGroup();
		// 补充信息

        org.setGuidEmp(guidEmp);
        org.setGuidGroup(guidGroup);
		return org;
	}
}

