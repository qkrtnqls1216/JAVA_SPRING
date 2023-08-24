package com.springboot.hello.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.springboot.hello.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{
    @Override
    ArrayList<Article> findAll();
}
