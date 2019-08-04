package com.example.updaytest.updaytest.service;

import com.example.updaytest.updaytest.exception.KeywordNotFoundException;
import com.example.updaytest.updaytest.model.Keyword;
import com.example.updaytest.updaytest.repository.KeywordRepository;
import com.example.updaytest.updaytest.service.KeywordService;
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
public class KeywordServiceTest {

    @Mock
    private KeywordRepository keywordRepository;

    @InjectMocks
    private KeywordService keywordService;

    @Test
    public void testFindAllReturningEmptyList(){
        //GIVEN
        when(keywordRepository.findAll()).thenReturn(Collections.emptyList());

        //WHEN
        List<Keyword> keywords = keywordService.findAll();

        //THEN
        Assert.assertEquals(keywords, Collections.emptyList());
        verify(keywordRepository, times(1)).findAll();
    }

    @Test
    public void testCreateKeyword(){
        Keyword keyword = new Keyword();
        keyword.setId(1l);
        keyword.setName("science");

        //GIVEN
        when(keywordRepository.save(keyword)).thenReturn(keyword);

        //WHEN
        Keyword keywordCreated = keywordService.create(keyword);

        //THEN
        Assert.assertEquals(keywordCreated, keyword);
        verify(keywordRepository, times(1)).save(keyword);
    }

    @Test
    public void testFindAllReturningValue(){
        Keyword keyword = new Keyword();
        keyword.setId(1l);
        keyword.setName("science");

        List<Keyword> keywords = new ArrayList<>();
        keywords.add(keyword);

        //GIVEN
        when(keywordRepository.findAll()).thenReturn(keywords);

        //WHEN
        keywordService.create(keyword);
        List<Keyword> returnedKeywords = keywordService.findAll();

        //THEN
        Assert.assertEquals(returnedKeywords, keywords);
        verify(keywordRepository, times(1)).findAll();
    }

    @Test(expected = KeywordNotFoundException.class)
    public void testAuthorExistanceValidation(){
        //GIVEN
        when(keywordRepository.findById(Long.valueOf(1))).thenReturn(Optional.empty());

        //WHEN
        keywordService.keywordExistanceValidation(Long.valueOf(1));
    }
}
