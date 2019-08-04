package com.example.updaytest.updaytest.exception;

public class KeywordNotFoundException extends EntityNotFoundException {
    public KeywordNotFoundException(Long id){
        super("Keyword not found with id: " + id);
    }
}
