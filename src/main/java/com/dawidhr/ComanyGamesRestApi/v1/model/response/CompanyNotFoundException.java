package com.dawidhr.ComanyGamesRestApi.v1.model.response;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
