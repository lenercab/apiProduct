package com.phone.phone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phone.phone.ApirestphoneApplication;
import com.phone.phone.controller.Mock.MockPhone;
import com.phone.phone.dto.*;
import com.phone.phone.service.PhoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringBootTest(classes = ApirestphoneApplication.class)
@WebAppConfiguration
public class PhoneControllerTest {

    @MockBean
    PhoneService phoneService;

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void phonePST() throws Exception {
        PhonePSTrq phonePSTrq = MockPhone.getPhonePSTrq();
        PhonePSTrs phonePSTrs = MockPhone.getPhonePSTrs();
        when(phoneService.insertPhone(phonePSTrq)).thenReturn(phonePSTrs);
        String uri = "/Phones";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(phonePSTrq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 201, status);
        assertNotNull(content);
    }

    @Test
    public void phonePST_when_it_is_bad_request() throws Exception {
        PhonePSTrq phonePSTrq = MockPhone.getPhonePSTrq();
        phonePSTrq.setNumberPhone("3135712334FEC");
        PhonePSTrs phonePSTrs = MockPhone.getPhonePSTrs();
        when(phoneService.insertPhone(phonePSTrq)).thenReturn(phonePSTrs);
        String uri = "/Phones";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(phonePSTrq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 400, status);
        assertNotNull(content);
    }

    @Test
    public void phonePTC() throws Exception {
        PhonePTCrq phonePTCrq = MockPhone.getPhonePTCrq();
        PhonePTCrs phonePTCrs = MockPhone.getPhonePTCrs();
        when(phoneService.updatePhone(phonePTCrq)).thenReturn(phonePTCrs);
        String uri = "/Phones";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                .content(asJsonString(phonePTCrq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 201, status);
        assertNotNull(content);

    }

    @Test
    public void getAllPhonesByCustomerId() throws Exception {
        PhonesGETALLrs phonesGETALLrs = MockPhone.getPhonesGETALLrs();
        when(phoneService.getAllPhoneByCustomerId(1l)).thenReturn(phonesGETALLrs);
        String uri = "/Customer/1/Phones";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }

    @Test
    public void getPhonesByIdAndCustomerId() throws Exception {
        PhoneGETrs phoneGETrs = MockPhone.getPhoneGETrs();
        when(phoneService.getPhoneByIdAndCustomerId(1l, 1l)).thenReturn(phoneGETrs);
        String uri = "/Customer/1/Phones/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }

    @Test
    public void deletePhonesById() throws Exception {

        Message message = new Message();
        message.setMessage("Phone deleted successfully");
        when(phoneService.deletePhoneById(1l, 1l)).thenReturn(message);
        String uri = "/Customer/1/Phones/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
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