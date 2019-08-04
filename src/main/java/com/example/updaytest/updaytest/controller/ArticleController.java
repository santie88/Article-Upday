package com.example.updaytest.updaytest.controller;

import com.example.updaytest.updaytest.exception.EntityNotFoundException;
import com.example.updaytest.updaytest.exception.ValueProvidedNullException;
import com.example.updaytest.updaytest.model.Article;
import com.example.updaytest.updaytest.service.ArticleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(@RequestBody Article article) {
        return saveOrUpdate(null, article, (a, b) -> articleService.create(b));
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable Long id, @RequestBody Article article) {
        return saveOrUpdate(id, article, (a, b) -> articleService.update(a, b));
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable Long id){
        return articleService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }

    @GetMapping()
    public List<Article> getArticlesInRange(@RequestParam("fromDate")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    LocalDate fromDate,
                                            @RequestParam("toDate")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    LocalDate toDate) {
        return articleService.findArticlesInRange(fromDate, toDate);
    }

    private Article saveOrUpdate(Long id, Article article, BiFunction<Long, Article, Article> biFunction){
        try {
            return biFunction.apply(id, article);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (ValueProvidedNullException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
