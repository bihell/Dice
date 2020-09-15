package com.bihell.dice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FrontControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getOption() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/tag")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }

    @Test
    public void category() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/category"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result : " + responseString);
    }

}