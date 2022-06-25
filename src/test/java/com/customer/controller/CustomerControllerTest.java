package com.customer.controller;

import com.google.gson.Gson;
import com.customer.ICustomerService;

import com.customer.model.CustomerRequest;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.UUID;

import static com.customer.util.FormatUtil.BASE_DATE_FORMAT;
import static com.customer.util.WebURLSUtil.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
@Log4j2
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerService iCustomerService;

    @Test
    public void createCustomerTest() throws Exception {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerId("test_customer_id");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BASE_DATE_FORMAT);
        String date = simpleDateFormat.format(new Date());
        customerRequest.setCreatedAt(date);

        Gson gson = new Gson();
        log.info(gson.toJson(customerRequest));

        String externalId = UUID.randomUUID().toString();

        when(iCustomerService.createCustomer(customerRequest)).thenReturn(externalId);

        mockMvc.perform(post(CUSTOMER_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(customerRequest)))
                .andExpect(status().isOk());

    }

    @Test
    public void getCustomerTest() throws Exception {

        String customerId = "test_customer_id";
        String externalId = UUID.randomUUID().toString();

        when(iCustomerService.getExternalId(customerId)).thenReturn(externalId);

        mockMvc.perform(get(CUSTOMER_BASE_PATH + "/" + customerId))
                .andExpect(status().isOk());

    }

}