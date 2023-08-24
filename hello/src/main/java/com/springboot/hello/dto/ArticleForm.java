package com.springboot.hello.dto;

import com.springboot.hello.entity.Article;

import lombok.AllArgsConstructor;
import lombok.ToString;;

// 모든 아큐먼트의 생성자를 만들어주는 어노테이션이다.
// 이로서 아래 주석의 생성자 생성코드가 필요없어진다.
@ToString
@AllArgsConstructor
public class ArticleForm {
    private Long id;
    private String title; // 제목
    private String content; // 내용

    public Article toEntity(){
        return new Article(id, title, content); // 새로운 객체를 생성하여 객체를 반환하기
    }
    // public ArticleForm(String title, String content){ // 생성자
    //     this.title = title;
    //     this.content = content;
    // }
}

