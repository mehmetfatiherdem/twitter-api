package com.example.demo.utils;

import java.util.regex.Pattern;

public class Email {

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();

    }
}
