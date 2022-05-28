package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        return id+" "+name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){ //dto 만든 후 클래스 객체 불러옴
        return user;
    }

    @Timer //우리가 직접 만든 annotation 붙여주기
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        //DB Logic
        Thread.sleep(1000*2); //1000이 1초니 2초정도 걸린다고 한다

    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);
        return user;
    }
}
