package com.group.presentation.customer;

import com.group.presentation.model.Customer;
import com.group.presentation.repository.CustomerRepository;
import com.group.presentation.service.CustomerRegistrationRequest;
import com.group.presentation.service.CustomerService;
import com.group.presentation.util.PhoneNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PhoneNumberValidator phoneNumberValidator;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CustomerService(customerRepository, phoneNumberValidator);
    }



    @Test
    void itShouldNotSaveNewCustomerWhenPhoneNumberIsInvalid() {
        // Given a phone number and a customer
        String phoneNumber = "000099";
        Customer customer = new Customer(UUID.randomUUID(), "Samuel", phoneNumber);

        // ... a request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);


        //... Valid phone number
        given(phoneNumberValidator.test(phoneNumber)).willReturn(false);

        // When
        assertThatThrownBy(() -> underTest.registerNewCustomer(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Phone Number " + phoneNumber + " is not valid");

        // Then
        then(customerRepository).shouldHaveNoInteractions();
    }



    @Test
    void itShouldThrowWhenPhoneNumberIsTaken() {
        // Given a phone number and a customer
        String phoneNumber = "000099";
        Customer customer = new Customer(UUID.randomUUID(), "Maryam", phoneNumber);
        Customer customerTwo = new Customer(UUID.randomUUID(), "John", phoneNumber);

        // ... a request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);

        // ... No customer with phone number passed
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber))
                .willReturn(Optional.of(customerTwo));

        //... Valid phone number
        given(phoneNumberValidator.test(phoneNumber)).willReturn(true);

        // When
        // Then
        assertThatThrownBy(() -> underTest.registerNewCustomer(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format("phone number [%s] is taken", phoneNumber));

        // Finally
        then(customerRepository).should(never()).save(any(Customer.class));

    }
}