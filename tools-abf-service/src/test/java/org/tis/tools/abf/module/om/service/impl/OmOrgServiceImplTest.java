package org.tis.tools.abf.module.om.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.om.entity.OmOrg;
import org.tis.tools.abf.module.om.service.IOmOrgService;

public class OmOrgServiceImplTest extends BaseTest {

    @Autowired
    IOmOrgService omOrgService;


    @Test
    public void genOrgCode() {
    }

    @Test
    public void createRootOrg() {
        String areaCode = "1111";
        String orgDegree = "2";
        String orgName = "1";
        String orgType = "1" ;
        OmOrg rootOrg = omOrgService.createRootOrg(areaCode, orgDegree, orgName, orgType);
        Assert.assertEquals(rootOrg.getOrgName(), orgName);
        Assert.assertEquals(rootOrg.getOrgType(), orgType);
        Assert.assertEquals(rootOrg.getOrgDegree(), orgDegree);
    }
}