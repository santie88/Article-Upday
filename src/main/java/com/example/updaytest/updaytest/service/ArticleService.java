package com.example.updaytest.updaytest.service;

import com.example.updaytest.updaytest.exception.ArticleNotFoundException;
import com.example.updaytest.updaytest.exception.AuthorNotFoundException;
import com.example.updaytest.updaytest.exception.AuthorValueProvidedNullException;
import com.example.updaytest.updaytest.exception.KeywordValueProvidedNullException;
import com.example.updaytest.updaytest.model.Article;
import com.example.updaytest.updaytest.model.Author;
import com.example.updaytest.updaytest.repository.ArticleRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private AuthorService authorService;
    private KeywordService keywordService;

    public ArticleService(ArticleRepository articleRepository, AuthorService authorService, KeywordService keywordService) {
        this.articleRepository = articleRepository;
        this.authorService = authorService;
        this.keywordService = keywordService;
    }

    public Article create(Article article) {
        validateAuthors(article);
        validateKeywords(article);

        return articleRepository.save(article);
    }

    public Article update(Long id, Article article) {
        articleExistanceValidation(id);
        validateAuthors(article);
        validateKeywords(article);

        Article articleToUpdate = articleRepository.findById(id).get();

        articleToUpdate.setHeader(article.getHeader());
        articleToUpdate.setShortDescription(article.getShortDescription());
        articleToUpdate.setText(article.getText());
        articleToUpdate.setPublishDate(article.getPublishDate());
        articleToUpdate.setAuthors(article.getAuthors());
        articleToUpdate.setKeywords(article.getKeywords());

        return articleRepository.save(articleToUpdate);
    }

    public void delete(Long id) {
        articleExistanceValidation(id);
        articleRepository.deleteById(id);
    }

    public Article getById(Long id){
        return articleRepository.findById(id)
                                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    public List<Article> findArticlesInRange(LocalDate fromDate, LocalDate toDate) {
        return articleRepository.findAll()
                .stream()
                .filter(article ->
                        !article.getPublishDate().isBefore(fromDate) &&
                        !article.getPublishDate().isAfter(toDate))
                .collect(Collectors.toList());
    }

    private void validateAuthors(Article article) {
        article.getAuthors().forEach(author -> authorService.authorExistanceValidation(
                Optional.ofNullable(author.getId())
                        .orElseThrow(() -> new AuthorValueProvidedNullException())));
    }

    private void validateKeywords(Article article) {
        article.getKeywords().forEach(keyword -> keywordService.keywordExistanceValidation(
                Optional.ofNullable(keyword.getId())
                .orElseThrow(() -> new KeywordValueProvidedNullException())));
    }

    private void articleExistanceValidation(Long id) {
        if (!articleRepository.findById(id).isPresent()) {
            throw new ArticleNotFoundException(id);
        }
    }

}
