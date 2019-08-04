package com.example.updaytest.updaytest.controller;

import com.example.updaytest.updaytest.model.Article;
import com.example.updaytest.updaytest.model.Author;
import com.example.updaytest.updaytest.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody Author author){
        return authorService.create(author);
    }

    @GetMapping()
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}/articles")
    public Set<Article> findAllArticlesByKeywordId(@PathVariable Long id){
        return authorService.findAllArticlesByKeyword(id);
    }
}
