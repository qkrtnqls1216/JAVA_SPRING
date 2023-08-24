package com.springboot.hello.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.hello.repository.ArticleRepository;
import java.util.List;

import com.springboot.hello.dto.ArticleForm;
import com.springboot.hello.entity.Article;


@Service
public class ArticleService {
   
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto){
        Article article = dto.toEntity(); // dto를 객체로 할당
        // 새로운 글이기 때문에 id값 없어야 함
        // 있으면... null 리턴
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article); // articleRepository에 저장
    }

    public Article update(Long id, ArticleForm dto){
        Article article = dto.toEntity();

        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()){
            return null;
        }

        // 정상
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id){
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }

        articleRepository.delete(target);
        return target;
    }
   
}