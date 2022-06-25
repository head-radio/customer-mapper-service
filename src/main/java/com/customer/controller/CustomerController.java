package com.customer.controller;

import com.customer.ICustomerService;
import com.customer.model.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.customer.util.WebURLSUtil.*;

@RestController
@RequestMapping(CUSTOMER_BASE_PATH)
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(iCustomerService.createCustomer(customerRequest));
    }

    @GetMapping(value = CUSTOMER_PATH_PARAM)
    public ResponseEntity<String> getCustomer(@PathVariable(CUSTOMER_ID) String customerId) {
        return ResponseEntity.ok(iCustomerService.getExternalId(customerId));
    }

}