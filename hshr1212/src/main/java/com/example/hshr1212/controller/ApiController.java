package com.example.hshr1212.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hyungsoo")
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello hyungsoo!";
    }

    @PostMapping("/password")
    public String password(){
        return "a";
    }
}
