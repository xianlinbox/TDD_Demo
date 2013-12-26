package com.xianlinbox.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.StringContains;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.internal.matchers.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
@WebAppConfiguration
public class ApiControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
                .andExpect(jsonPath("$.requestId").value(1))
                .andExpect(jsonPath("$.requestType").value("GET"));
    }

    @Test
    public void shouldGetStatus404WhenRquestIdBiggerThan10() throws Exception {
        mockMvc.perform(get("/requests/11")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "xianlinbox")
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Request id must less than 10"));

    }

    @Test
    public void shouldGetStatus404WhenRquestIdOutOfIntegerRanger() throws Exception {
        mockMvc.perform(get("/requests/11123456789")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "xianlinbox")
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Request id must be an integer"));

    }

    @Test
    public void shouldGetStatus404WhenRquestIdIsNotInteger() throws Exception {
        mockMvc.perform(get("/requests/123ss")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "xianlinbox")
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Request id must be an integer"));

    }

    @Test
    public void shouldGetStatus500WhenUnexpectedErrorHappen() throws Exception {
        mockMvc.perform(get("/requests/10")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "xianlinbox")
        )
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Unexpected Server Error"));
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post("/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("this is the message")
                .param("userId", "xianlinbox")
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value("xianlinbox"))
                .andExpect(jsonPath("$.requestType").value("POST"))
                .andExpect(jsonPath("$.message").value("this is the message"));
    }

    @Test
    public void testPropagate() throws Exception {
        mockMvc.perform(post("/propagate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"xianlinbox\",\"dateTime\":\"2013-12-25 10:10:00\"}")
        )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.userId").value("xianlinbox"))
                .andExpect(jsonPath("$.dateTime").value("2013-12-25 10:10:00"));
    }


    @Test
    public void testPropagateWithIllegalUserId() throws Exception {
        mockMvc.perform(post("/propagate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"xianlinbox2\",\"dateTime\":\"2013-12-25 10:10:00\"}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("001"))
                .andExpect(jsonPath("$.message").value("Invalid Request:\nuserId must less than 10 digits\n"));
    }

    @Test
    public void testPropagateWithFutureDateTime() throws Exception {
        mockMvc.perform(post("/propagate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"xianlinbox\",\"dateTime\":\"2014-12-25 10:10:00\"}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("001"))
                .andExpect(jsonPath("$.message").value("Invalid Request:\nDateTime should not in future\n"));
    }


    @Test
    public void testPropagateWithWrongFormatDateTime() throws Exception {
        mockMvc.perform(post("/propagate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"xianlinbox\",\"dateTime\":\"2014-12-25\"}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("001"))
                .andExpect(jsonPath("$.message").value("Invalid format: \"2014-12-25\" is too short"));
    }

    @Test
    public void testPropagateWithBothIllegalUserIdAndIllegalDateTime() throws Exception {
        mockMvc.perform(post("/propagate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"xianlinbox2\",\"dateTime\":\"2014-12-25 10:10:00\"}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("001"))
                .andExpect(jsonPath("$.message").value(containsString("DateTime should not in future")))
                .andExpect(jsonPath("$.message").value(containsString("userId must less than 10 digits")));
    }
}
