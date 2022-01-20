package com.group.presentation.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PhoneNumberValidatorTest {
    private PhoneNumberValidator underTest;

    @BeforeEach
    void setUp(){
        underTest = new PhoneNumberValidator();
    }

    @Test
    @DisplayName("SHOULD VALIDATE PHONE NUMBER")
    void itShouldValidatePhoneNumber(){
        // Given
           String phoneNumber = "+250780531795";

           // When
        boolean isValid = underTest.test(phoneNumber);

        // then
        assertThat(isValid).isTrue();

    }
    
    @Test
    @DisplayName("PHONE NUMBER GREATER THAN 13")
    void itShouldValidatePhoneNumberWhenLengthIsLongerThan13(){
        // Given
        String phoneNumber = "+2507805317957";

        // When
        boolean isValid = underTest.test(phoneNumber);

        // then
        assertThat(isValid).isFalse();
    }


    @Test
    @DisplayName("PHONE NUMBER LESS THAN 13")
    void itShouldValidatePhoneNumberWhenLengthIsLessThan13(){
        // Given
        String phoneNumber = "+25078053179";

        // When
        boolean isValid = underTest.test(phoneNumber);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("PHONE NUMBER DOES NOT START WITH COUNTRY CODE")
    void itShouldValidatePhoneNumberWhenDoesNotStartWithCountryCode(){
        // Given
        String phoneNumber = "078053179";

        // When
        boolean isValid = underTest.test(phoneNumber);

        // then
        assertThat(isValid).isFalse();
    }


}
