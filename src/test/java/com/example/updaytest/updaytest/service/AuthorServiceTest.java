package com.example.updaytest.updaytest.service;

import com.example.updaytest.updaytest.exception.AuthorNotFoundException;
import com.example.updaytest.updaytest.model.Author;
import com.example.updaytest.updaytest.repository.AuthorRepository;
import com.example.updaytest.updaytest.service.AuthorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testFindAllReturningEmptyList(){
        //GIVEN
        when(authorRepository.findAll()).thenReturn(Collections.emptyList());

        //WHEN
        List<Author> authors = authorService.findAll();

        //THEN
        Assert.assertEquals(authors, Collections.emptyList());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testCreateAuthor(){
        Author author = new Author();
        author.setId(1l);
        author.setName("john");

        //GIVEN
        when(authorRepository.save(author)).thenReturn(author);

        //WHEN
        Author authorCreated = authorService.create(author);

        //THEN
        Assert.assertEquals(authorCreated, author);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testFindAllReturningValue(){
        Author author = new Author();
        author.setId(1l);
        author.setName("science");

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        //GIVEN
        when(authorRepository.findAll()).thenReturn(authors);

        //WHEN
        authorService.create(author);
        List<Author> returnedAuthors = authorService.findAll();

        //THEN
        Assert.assertEquals(returnedAuthors, authors);
        verify(authorRepository, times(1)).findAll();
    }

    @Test(expected = AuthorNotFoundException.class)
    public void testAuthorExistanceValidation(){
        //GIVEN
        when(authorRepository.findById(Long.valueOf(1))).thenReturn(Optional.empty());

        //WHEN
        authorService.authorExistanceValidation(Long.valueOf(1));
    }
}
