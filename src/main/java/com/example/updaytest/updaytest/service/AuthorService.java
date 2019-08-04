package com.example.updaytest.updaytest.service;

import com.example.updaytest.updaytest.exception.AuthorNotFoundException;
import com.example.updaytest.updaytest.model.Article;
import com.example.updaytest.updaytest.model.Author;
import com.example.updaytest.updaytest.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author create(Author author){
        return authorRepository.save(author);
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Set<Article> findAllArticlesByKeyword(Long id){
        return authorRepository.findById(id)
                .get()
                .getArticles();
    }

    public void authorExistanceValidation(Long id){
        if(!authorRepository.findById(id).isPresent()){
            throw new AuthorNotFoundException(id);
        }
    }

}
