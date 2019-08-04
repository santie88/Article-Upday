package com.example.updaytest.updaytest.service;

import com.example.updaytest.updaytest.exception.ArticleNotFoundException;
import com.example.updaytest.updaytest.model.Article;
import com.example.updaytest.updaytest.model.Author;
import com.example.updaytest.updaytest.model.Keyword;
import com.example.updaytest.updaytest.repository.ArticleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private KeywordService keywordService;
    @Mock
    private AuthorService authorService;
    @InjectMocks
    private ArticleService articleService;

    Article article;

    List<Article> articles;

    Author author;

    Set<Author> authors;

    Keyword keyword;

    Set<Keyword> keywords;

    @Before
    public void InitialSetup() {
        author = new Author();
        author.setId(1l);
        author.setName("science");

        authors = new HashSet<>();
        authors.add(author);

        keyword = new Keyword();
        keyword.setId(1l);
        keyword.setName("science");

        keywords = new HashSet<>();
        keywords.add(keyword);

        article = new Article();
        article.setId(1l);
        article.setHeader("header");
        article.setShortDescription("Short Description");
        article.setText("Text");
        article.setPublishDate(LocalDate.now());
        article.setAuthors(authors);
        article.setKeywords(keywords);

        articles = new ArrayList<>();
        articles.add(article);
    }

    @Test
    public void testCreateArticle() {
        //GIVEN
        when(articleRepository.save(article)).thenReturn(article);

        //WHEN
        Article articleCreated = articleService.create(article);

        //THEN
        Assert.assertEquals(articleCreated, article);
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    public void testFindArticlesInRange_returnOne() {
        //GIVEN
        when(articleRepository.findAll()).thenReturn(articles);

        //WHEN
        List<Article> articlesInRange = articleService.findArticlesInRange(
                LocalDate.now(),
                LocalDate.now().plusDays(1));

        //THEN
        Assert.assertEquals(articlesInRange, articles);
    }

    @Test
    public void testFindArticlesInRange_returnNone() {
        //GIVEN
        when(articleRepository.findAll()).thenReturn(articles);

        //WHEN
        List<Article> articlesInRange = articleService.findArticlesInRange(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2));

        //THEN
        Assert.assertEquals(articlesInRange, Collections.emptyList());
    }

    @Test(expected = ArticleNotFoundException.class)
    public void testAuthorExistanceValidation(){
        //GIVEN
        when(articleRepository.findById(Long.valueOf(1))).thenReturn(Optional.empty());

        //WHEN
        articleService.update(Long.valueOf(1), article);
    }
}
