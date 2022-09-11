package com.example.demo.utils.validation;

import com.example.demo.exception.email.InvalidEmailException;
import com.example.demo.utils.Email;

public class UserRegisterValidation {
    private static final String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String gmailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
            + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";

    public static Boolean isEmailValid(String email) throws InvalidEmailException {
        boolean isValidDefaultEmail = Email.patternMatches(email, emailRegexPattern);
        boolean isValidGmail = Email.patternMatches(email, gmailRegexPattern);

        if(!isValidDefaultEmail && !isValidGmail) throw new InvalidEmailException();

        return true;
    }
}
