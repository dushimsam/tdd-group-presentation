package com.group.presentation.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PhoneNumberValidatorTestParameterized {
    private PhoneNumberValidator underTest;

    @BeforeEach
    void setUp(){
        underTest = new PhoneNumberValidator();
    }

     @ParameterizedTest
     @CsvSource({"+447549594545,false","+250780531795,true","250780531795,false"})
     @DisplayName("TEST THREE CASES SIMULTANEOUSLY")
    void itShouldValidatePhoneNumber(String phoneNumber,String expected){
        // when 
         boolean isValid = underTest.test(phoneNumber);
         assertThat(isValid).isEqualTo(Boolean.valueOf(expected));
     }
}
