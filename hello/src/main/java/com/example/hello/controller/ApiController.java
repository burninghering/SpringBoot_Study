package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //해당 클래스는 REST API를 처리하는 Controller 라고 스프링이 인식하게 함
@RequestMapping("/api") //RequestMapping URI를 지정해주는 Annotation
public class ApiController {

    @GetMapping("/hello") //http://localhost:8080/api/hello
    public String hello(){
        return "Hello Spring Boot!";
    }

}
