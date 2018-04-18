package org.tis.tools.asf.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tis.tools.asf.ToolsAsfServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsAsfServiceApplication.class)
@WebAppConfiguration
public class BaseTest {

    @Autowired
    WebApplicationContext context;

    protected MockMvc mvc;

    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new EmployeeController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
