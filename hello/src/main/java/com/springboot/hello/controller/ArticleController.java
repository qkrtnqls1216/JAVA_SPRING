package com.springboot.hello.controller;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.springboot.hello.dto.ArticleForm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.hello.entity.Article;
import com.springboot.hello.repository.ArticleRepository;
import com.springboot.hello.service.ArticleService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Controller //해당클래스를 controller가 사용하고 있다.
public class ArticleController {
    @Autowired //의존성 주입
    private ArticleRepository articleRepository;
    @GetMapping(value = "/articles/new")
    public String newAriticleForm(){
        return "articles/new"; //http://localhost:8080/articles/new
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId(); // 글 작성 후 다시 페이지 이동 => saved.getId()시 개시글 id경로로 페이지 이동
    }

    // 동적으로 경로에 맵핑하기
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // id를 이용해서 데이터 조회 
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // id값으로 데이터를 찾음 -> id가 없으면 null 리턴 
        // id값이 있으면 articleEntity 변수에 값을 리턴 

        // model에 데이터 등록 
        model.addAttribute("articles", articleEntity);
        return "articles/show";
    }

    // index 가져오기
    // http://localhost:8080/articles
    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기 
        List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
        // 2. 모델에 데이터 등록 
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 전송
        return "articles/index";
    }

    //수정페이지
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("articles", articleEntity);
        return "articles/edit";
    }

    // 수정페이지 저장
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        Article article = form.toEntity();
        log.info(article.toString());
        Article target = articleRepository.findById(article.getId()).orElse(null);
        if(target != null){
            articleRepository.save(article);
        }

        return "redirect:/articles/" + article.getId();
    }

    // 삭제페이지
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제완료");
        }
        return "redirect:/articles";
    }
}