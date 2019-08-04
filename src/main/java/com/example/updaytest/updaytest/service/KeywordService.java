package com.example.updaytest.updaytest.service;

import com.example.updaytest.updaytest.exception.AuthorNotFoundException;
import com.example.updaytest.updaytest.exception.KeywordNotFoundException;
import com.example.updaytest.updaytest.model.Article;
import com.example.updaytest.updaytest.model.Keyword;
import com.example.updaytest.updaytest.repository.KeywordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class KeywordService {

    private KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository){
        this.keywordRepository = keywordRepository;
    }

    public Keyword create(Keyword keyword){
        return keywordRepository.save(keyword);
    }

    public List<Keyword> findAll(){
        return keywordRepository.findAll();
    }

    public Set<Article> findAllArticlesByKeyword(Long id){
        return keywordRepository.findById(id)
                .get()
                .getArticles();
    }

    public void keywordExistanceValidation(Long id){
        if(!keywordRepository.findById(id).isPresent()){
            throw new KeywordNotFoundException(id);
        }
    }

}
