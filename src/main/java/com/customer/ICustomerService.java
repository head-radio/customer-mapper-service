package com.customer;

import com.customer.model.CustomerRequest;

public interface ICustomerService {

    String createCustomer(CustomerRequest customerRequest);

    String getExternalId(String customerId);

}
