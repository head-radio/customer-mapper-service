package com.customer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ExternalServiceTest {

    @InjectMocks
    private ExternalService externalService;

    @Test
    public void createExternalIdTest() throws Exception {

        String result = externalService.createExternalId("customer_id");
        assertNotNull(result);

    }

}