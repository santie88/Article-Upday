package com.example.updaytest.updaytest.exception;

public class AuthorValueProvidedNullException extends ValueProvidedNullException {
    public AuthorValueProvidedNullException() {
        super("The Author id must be provided");
    }
}
