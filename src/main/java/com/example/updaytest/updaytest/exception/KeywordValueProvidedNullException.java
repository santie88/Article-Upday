package com.example.updaytest.updaytest.exception;

public class KeywordValueProvidedNullException extends ValueProvidedNullException {
    public KeywordValueProvidedNullException(){
        super("The Keyword id must be provided");
    }
}
