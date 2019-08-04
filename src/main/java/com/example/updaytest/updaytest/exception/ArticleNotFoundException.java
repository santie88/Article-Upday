package com.example.updaytest.updaytest.exception;

public class ArticleNotFoundException extends EntityNotFoundException {
    public ArticleNotFoundException(Long id){
        super("Article not found with id: " + id);
    }
}
