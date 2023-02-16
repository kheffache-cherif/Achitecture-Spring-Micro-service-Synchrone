package com.example.billingservice.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String messages){
        super(messages);
    }
}
