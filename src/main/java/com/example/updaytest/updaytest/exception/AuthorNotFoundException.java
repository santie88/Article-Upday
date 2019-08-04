package com.example.updaytest.updaytest.exception;

public class AuthorNotFoundException extends EntityNotFoundException {
    public AuthorNotFoundException(Long id){
        super("Author not found with id: " + id);
    }
}
