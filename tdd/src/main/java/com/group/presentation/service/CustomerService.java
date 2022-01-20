package com.group.presentation.service;

import com.group.presentation.model.Customer;
import com.group.presentation.repository.CustomerRepository;
import com.group.presentation.util.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PhoneNumberValidator phoneNumberValidator;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                                       PhoneNumberValidator phoneNumberValidator) {
        this.customerRepository = customerRepository;
        this.phoneNumberValidator = phoneNumberValidator;
    }

    public void registerNewCustomer(CustomerRegistrationRequest request) {
        String phoneNumber = request.getCustomer().getPhoneNumber();

        if (!phoneNumberValidator.test(phoneNumber)) {
            throw new IllegalStateException("Phone Number " + phoneNumber + " is not valid");
        }

        Optional<Customer> customerOptional = customerRepository
                .selectCustomerByPhoneNumber(phoneNumber);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getName().equals(request.getCustomer().getName())) {
                return;
            }
            throw new IllegalStateException(String.format("phone number [%s] is taken", phoneNumber));
        }

        if(request.getCustomer().getId() == null) {
            request.getCustomer().setId(UUID.randomUUID());
        }

        customerRepository.save(request.getCustomer());
    }
}