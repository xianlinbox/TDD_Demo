package com.xianlinbox.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
public class ApiControllerIntegrationTest {

    @Autowired
    private ApiController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("userId", "xianlinbox")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("xianlinbox"))
                .andExpect(jsonPath("$.requestId").value("1"))
                .andExpect(jsonPath("$.requestType").value("GET"));
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post("/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("this is the message")
                .param("userId", "xianlinbox")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("xianlinbox"))
                .andExpect(jsonPath("$.requestType").value("POST"))
                .andExpect(jsonPath("$.message").value("this is the message"));
    }

}
