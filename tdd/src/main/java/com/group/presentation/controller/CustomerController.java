package com.group.presentation.controller;

import com.group.presentation.service.CustomerRegistrationRequest;
import com.group.presentation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer-registration")
public class CustomerController {

    private final CustomerService customerRegistrationService;

    @Autowired
    public CustomerController(CustomerService customerRegistrationService) {
        this.customerRegistrationService = customerRegistrationService;
    }

    @PutMapping
    public void registerNewCustomer(
            @RequestBody CustomerRegistrationRequest request) {
        customerRegistrationService.registerNewCustomer(request);
    }

}
