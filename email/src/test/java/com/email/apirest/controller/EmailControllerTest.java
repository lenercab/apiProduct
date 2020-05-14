package com.email.apirest.controller;

import com.email.apirest.EmailApplication;
import com.email.apirest.controller.Mock.MockEmail;
import com.email.apirest.dto.*;
import com.email.apirest.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmailApplication.class)
@WebAppConfiguration
public class EmailControllerTest {

    @MockBean
    EmailService emailService;

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getByIdEmail() throws Exception {
        EmailGETById emailGETById = MockEmail.getEmailGETById();
        when(emailService.getByIdEmail(1l, 1l)).thenReturn(emailGETById);
        String uri = "/Customers/1/Emails/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }

    @Test
    public void getAllEmails()throws Exception {
        EmailGETALL emailGETALL = MockEmail.getEmailGETALL();
        when(emailService.getAllEmails(1l)).thenReturn(emailGETALL);
        String uri = "/Customers/1/Emails";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }

    @Test
    public void emailPST() throws Exception{
        EmailPSTRq emailPSTRq = MockEmail.getEmailPSTRq();
        EmailPSTRs emailPSTRs = MockEmail.getEmailPSTRs();
        when(emailService.saveEmail(emailPSTRq)).thenReturn(emailPSTRs);
        String uri = "/Emails";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(emailPSTRq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 201, status);
        assertNotNull(content);

    }

    @Test public void emailPST_when_not_comply_with_format_fiel_email() throws Exception{
        EmailPSTRq emailPSTRq = MockEmail.getEmailPSTRq();
        emailPSTRq.setEmail("lenner");
        EmailPSTRs emailPSTRs = MockEmail.getEmailPSTRs();
        when(emailService.saveEmail(emailPSTRq)).thenReturn(emailPSTRs);
        String uri = "/Emails";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(emailPSTRq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 400, status);
        assertNotNull(content);

    }

    @Test
    public void emailPTC() throws Exception{

        EmailPTCRq emailPTCRq = MockEmail.getEmailPTCRq();
        EmailPTCRs emailPTCRs = MockEmail.getEmailPTCRs();
        when(emailService.updateEmail(emailPTCRq)).thenReturn(emailPTCRs);
        String uri = "/Emails";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                .content(asJsonString(emailPTCRq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 201, status);
        assertNotNull(content);
    }

    @Test
    public void emailDeleteById() throws Exception {
        Message message = new Message();
        message.setMessage("Email deleted successfully: id: 1");
        when(emailService.deleteEmailById(1l, 1l)).thenReturn(message);
        String uri = "/Customers/1/Emails/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}