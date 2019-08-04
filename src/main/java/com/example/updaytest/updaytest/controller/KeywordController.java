package com.example.updaytest.updaytest.controller;

import com.example.updaytest.updaytest.model.Article;
import com.example.updaytest.updaytest.model.Keyword;
import com.example.updaytest.updaytest.service.KeywordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/keywords")
public class KeywordController {

    private KeywordService keywordService;

    public KeywordController (KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Keyword create(@RequestBody Keyword keyword){
        return keywordService.create(keyword);
    }

    @GetMapping()
    public List<Keyword> findAll(){
        return keywordService.findAll();
    }

    @GetMapping("/{id}/articles")
    public Set<Article> findAllArticlesByKeywordId(@PathVariable Long id){
        return keywordService.findAllArticlesByKeyword(id);
    }
}
