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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SnippetControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void saveSnippet() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "");
        params.add("title", "text title 1");
        params.add("content","adf asdf ");
        params.add("tags", "summary,asdf,asdfas,ffff");
        params.add("created", "1565511562000");
        params.add("modified", "1565511562000");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/admin/snippet").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    @Test
    public void updateSnippet() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "10");
        params.add("title", "text title 1");
        params.add("content","adf asdf ");
        params.add("tags", "summary,js");
        params.add("created", "1565511562000");
        params.add("modified", "1565511562000");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/admin/snippet").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

}