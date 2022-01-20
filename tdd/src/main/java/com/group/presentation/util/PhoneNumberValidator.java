package com.group.presentation.util;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
@Component
public class PhoneNumberValidator implements Predicate<String> {
    @Override
    public boolean test(String phoneNumber){
        return phoneNumber.startsWith("+250") && phoneNumber.length() == 13;
    }
}
