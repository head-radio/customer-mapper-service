package com.customer.service;

import com.customer.exception.BadRequestException;
import com.customer.exception.NotFoundtException;
import com.customer.exception.UnprocessableEntityException;
import com.customer.model.CustomerRequest;
import com.customer.persistence.model.Customer;
import com.customer.persistence.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.customer.util.FormatUtil.BASE_DATE_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ExternalService externalService;

    @Test
    public void createCustomerTest() throws Exception {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerId("test_customer_id");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BASE_DATE_FORMAT);
        String date = simpleDateFormat.format(new Date());
        customerRequest.setCreatedAt(date);

        when(externalService.createExternalId(any())).thenReturn(UUID.randomUUID().toString());

        String result = customerService.createCustomer(customerRequest);
        assertNotNull(result);
        assertEquals(36, result.length());

    }

    @Test(expected = BadRequestException.class)
    public void createCustomerTest_dateFormatException() throws Exception {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerId("test_customer_id");

        customerRequest.setCreatedAt("2022-07");

        customerService.createCustomer(customerRequest);

    }

    @Test(expected = BadRequestException.class)
    public void createCustomerTest_invalidDateException() throws Exception {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerId("test_customer_id");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BASE_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        String date = simpleDateFormat.format(calendar.getTime());
        customerRequest.setCreatedAt(date);

        customerService.createCustomer(customerRequest);

    }

    @Test(expected = UnprocessableEntityException.class)
    public void createCustomerTest_externalServiceException() throws Exception {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerId("test_customer_id");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BASE_DATE_FORMAT);
        String date = simpleDateFormat.format(new Date());
        customerRequest.setCreatedAt(date);


        when(externalService.createExternalId(any())).thenThrow(new UnprocessableEntityException(""));

        customerService.createCustomer(customerRequest);

    }

    @Test
    public void getExternalIdTest() throws Exception {

        Customer customer = new Customer();
        customer.setCustomerId("test_customer_id");
        customer.setCreatedAt(new Date());
        customer.setExternalId("2b1870a93641356c00a2b0c30edd4b1b");

        Optional<Customer> optionalCustomer = Optional.of(customer);
        when(customerRepository.findById(any())).thenReturn(optionalCustomer);

        String result = customerService.getExternalId(customer.getCustomerId());
        assertNotNull(result);
        assertEquals(customer.getExternalId(), result);

    }

    @Test(expected = NotFoundtException.class)
    public void getExternalIdTest_exception() throws Exception {

        Optional<Customer> optionalCustomer = Optional.empty();
        when(customerRepository.findById(any())).thenReturn(optionalCustomer);

        customerService.getExternalId("customer_id");

    }

}