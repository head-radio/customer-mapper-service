package com.customer.service;

import com.customer.ICustomerService;

import com.customer.IExternalService;
import com.customer.exception.BadRequestException;
import com.customer.exception.NotFoundtException;
import com.customer.exception.UnprocessableEntityException;
import com.customer.model.CustomerRequest;
import com.customer.persistence.model.Customer;
import com.customer.persistence.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.customer.util.FormatUtil.BASE_DATE_FORMAT;

@org.springframework.stereotype.Service
@Log4j2
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IExternalService iExternalService;

    @Override
    public String createCustomer(CustomerRequest customerRequest) {

        log.info(customerRequest);

        Customer customerEntity = new Customer();
        customerEntity.setCustomerId(customerRequest.getCustomerId());

        Date dateInput;
        try {
            dateInput = new SimpleDateFormat(BASE_DATE_FORMAT).parse(customerRequest.getCreatedAt());
            customerEntity.setCreatedAt(dateInput);
        } catch (ParseException e) {
            log.warn("Error during convert dateInput - input value " + customerRequest.getCreatedAt());
            throw new BadRequestException("invalid format data");
        }

        if (new Date().before(dateInput)) {
            throw new BadRequestException("dateInput cannot be in the future");
        }

        String externalId;
        try {
            externalId = iExternalService.createExternalId(customerRequest.getCustomerId());
            customerEntity.setExternalId(externalId);
        } catch (Exception e) {
            log.warn("error during call external service for external id creation - input value " + customerRequest.getCustomerId());
            throw new UnprocessableEntityException("external service error");
        }

        customerRepository.save(customerEntity);

        return externalId;

    }

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
