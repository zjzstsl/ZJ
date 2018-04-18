package org.tis.tools.asf.module.er.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tis.tools.asf.base.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


public class ERAppControllerTest extends BaseTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new EmployeeController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAdd() throws Exception {
        // 测试UserController
        RequestBuilder request = null;

        // 1、get查一下user列表，应该为空
        request = post("/erApp/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"name\":\"应用1\",\"desc\": \"测试描述\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"code\":\"200\",\"msg\":\"新增成功！\",\"result\":null}")));
    }
}