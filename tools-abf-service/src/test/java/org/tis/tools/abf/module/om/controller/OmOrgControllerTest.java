package org.tis.tools.abf.module.om.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.tis.tools.abf.base.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class OmOrgControllerTest extends BaseTest {

    @Test
    public void add() throws Exception {
        RequestBuilder request = null;
        request = post("/omOrg/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"areaCode\":\"1111\",\"orgDegree\": \"2\", \"orgName\": \"1\", \"orgType\":\"1\", \"guidParents\": \"1\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }
}