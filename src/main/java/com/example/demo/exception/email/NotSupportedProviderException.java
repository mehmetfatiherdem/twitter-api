package com.example.demo.exception.email;

public class NotSupportedProviderException extends RuntimeException{
    public NotSupportedProviderException(String provider){
        super("The provider \"" + provider + "\" is not supported");
    }
}
