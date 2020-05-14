package com.address.apirest.controller;

import com.address.apirest.ApirestaddressApplication;
import com.address.apirest.controller.Mock.MockAddress;
import com.address.apirest.dto.*;
import com.address.apirest.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@SpringBootTest(classes = ApirestaddressApplication.class)
@WebAppConfiguration
public class AddressControllerTest {

    @MockBean
    AddressService addressService;

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addressPST() throws Exception{
        AddressPSTrq addressPSTrq = MockAddress.getAddressPSTrq();
        AddressPSTrs addressPSTrs = MockAddress.getAddressPSTrs();
        when(addressService.insertAddress(addressPSTrq)).thenReturn(addressPSTrs);
        String uri = "/Addresses";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(addressPSTrq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 201, status);
        assertNotNull(content);

    }

    @Test
    public void addressPTC() throws Exception{
        AddressPTCrq addressPTCrq = MockAddress.getAddressPTCrq();
        AddressPTCrs addressPTCrs = MockAddress.getAddressPTCrs();
        when(addressService.updateAddress(addressPTCrq)).thenReturn(addressPTCrs);
        String uri = "/Addresses";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(addressPTCrq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 201, status);
        assertNotNull(content);
    }

    @Test
    public void getAllAddressesByCustomerId() throws Exception{
        AddressGETrs addressGETrs = MockAddress.getAddressGETrs();
        when(addressService.getAddressByIdAndCustomerId(1l, 1l)).thenReturn(addressGETrs);
        String uri = "/Customer/1/Addresses/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }

    @Test
    public void getAddressByIdAndCustomerId()throws Exception {
        AddressGETALLrs addressGETALLrs = MockAddress.getAddressGETALLrs();
        when(addressService.getAllAddressByCustomerId( 1l)).thenReturn(addressGETALLrs);
        String uri = "/Customer/1/Addresses";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("status code incorrect", 200, status);
        assertNotNull(content);
    }

    @Test
    public void deleteAddressById()throws Exception {
        Message message = new Message();
        message.setMessage("Address deleted successfully");
        when(addressService.deleteAdressyId(1l, 1l)).thenReturn(message);
        String uri = "/Customer/1/Addresses/1";
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