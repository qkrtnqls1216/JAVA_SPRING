package com.springboot.hello.api;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hello.dto.ArticleForm;
import com.springboot.hello.entity.Article;
import com.springboot.hello.repository.ArticleRepository;
import com.springboot.hello.service.ArticleService;


@RestController
public class ArticleApiController {
    // @Autowired
    // private ArticleRepository articleRepository;

    // // http://localhost:8080/api/articles
    // @GetMapping("/api/articles")
    // public List<Article> index(){
    //     return articleRepository.findAll();
    // }

    @Autowired
    private ArticleService articleService;

    // http://localhost:8080/api/articles
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    // http://localhost:8080/api/articles/{id}
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    // http://localhost:8080/api/articles
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
            ResponseEntity.status(HttpStatus.OK).body(created) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                        @RequestBody ArticleForm dto){
            Article updated = articleService.update(id, dto);
            return (updated != null) ?
            ResponseEntity.status(HttpStatus.OK).body(updated) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
            ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    

}