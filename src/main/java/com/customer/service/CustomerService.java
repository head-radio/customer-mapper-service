package com.customer.service;

import com.customer.ICustomerService;

import com.customer.IExternalService;
import com.customer.IValidationService;
import com.customer.exception.BadRequestException;
import com.customer.exception.NotFoundtException;
import com.customer.exception.UnprocessableEntityException;
import com.customer.model.CustomerRequest;
import com.customer.persistence.model.Customer;
import com.customer.persistence.repository.CustomerRepository;

import com.customer.util.FormatUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
@Log4j2
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IExternalService iExternalService;

    @Autowired
    private IValidationService iValidationService;

    /**
     * create customer and store it in the persistence layer
     * @param customerRequest
     * @return the externalId created by external service during the method
     */
    @Override
    public String createCustomer(CustomerRequest customerRequest) {

        // validate syntax object format
        iValidationService.validateObject(customerRequest);

        // transform to date
        Date dateInput = FormatUtil.getDate(customerRequest.getCreatedAt());

        // check createdAt cannot be in the future
        if (new Date().before(dateInput)) {
            throw new BadRequestException("dateInput cannot be in the future");
        }

        // call external service to create external id
        String externalId;
        try {
            externalId = iExternalService.createExternalId(customerRequest.getCustomerId());
        } catch (Exception e) {
            log.warn("error during call external service for external id creation - input value " + customerRequest.getCustomerId());
            throw new UnprocessableEntityException("external service error");
        }

        Customer customerEntity = new Customer();
        customerEntity.setCustomerId(customerRequest.getCustomerId());
        customerEntity.setCreatedAt(dateInput);
        customerEntity.setExternalId(externalId);

        customerRepository.save(customerEntity);

        return externalId;

    }

    /**
     * Retrieve the customer external id by the customer id
     * @param customerId
     * @return external id
     */
    @Override
    public String getExternalId(String customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isEmpty()) {
            throw new NotFoundtException("customer does not exist");
        }

        Customer customer = optionalCustomer.get();

        return customer.getExternalId();

    }
}
