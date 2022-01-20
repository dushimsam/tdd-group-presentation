package com.group.presentation.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group.presentation.model.Customer;

public class CustomerRegistrationRequest {

    private final Customer customer;

    public CustomerRegistrationRequest(
            @JsonProperty("customer") Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "CustomerRegistrationRequest{" +
                "customer=" + customer +
                '}';
    }
}
