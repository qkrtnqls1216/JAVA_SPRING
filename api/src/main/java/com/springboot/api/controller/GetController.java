package com.springboot.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.dto.MemberDto;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/get-api")  // get-api가 실제 api의 값이다. 그 앞의 경로는 api는 맞지만 request가 없기때문에 값은 아니다.
public class GetController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello(){
        return "hello encore!!"; // http://encore/api/v1/get-api/hello
    }

    @GetMapping(value = "/name")
    public String getNmae(){
        return "haha"; // http://encore/api/v1/get-api/name
    }

    // 동기 방식
    @GetMapping(value = "/variable1/{variable}") //내가 적어본 공간 uri를 통해 get하게 된다. http://encore/api/v1/get-api/variable1/asfasdfadsfasdf 실행해보기
    public String getVaString(@PathVariable String variable){
        return variable;
    }

    // 비동기 방식
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){ // http://encore/api/v1/get-api/variable2/배고프다
        return var;
    }

    @GetMapping(value = "/request1") // api값
    public String getRequestParam1(
        @RequestParam String name,
        @RequestParam String email, 
        @RequestParam String organization){
            return name + " " + email + " " + organization;
        } //http://encore/api/v1/get-api/request1?name=박수빈&email=qkrtnqls1216@naver.com&organization=encore
                                                // keys=value
        @GetMapping(value = "/request2")
        public String getRequestParam2(@RequestParam Map<String, String> param){
            StringBuilder sb = new StringBuilder();
            param.entrySet().forEach(map -> {
                sb.append(map.getKey() + " : " + map.getValue() + "\n");
            });
            return sb.toString();
        } // http://encore/api/v1/get-api/request2?name=encore&email=ddd&organization=dongjak

        @GetMapping(value="/request3")
        public String getRequestParam3(MemberDto memberDto){
            return memberDto.toString();
        } // http://encore/api/v1/get-api/request3?name=sb&email=gmail&organization=dongjak
}
