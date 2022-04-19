package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //HTML 파일을 찾게 됨
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html"; //@Controller는 String으로 return 하면 리소스파일을 찾게 됨
    }

    // ResponseEntity

    @ResponseBody //객체 자체를 return했을 때 리소스를 안찾고, ResponseBody를 만들어 보내겠다는 뜻
    @GetMapping("/user")
    public User user(){

        //원래는 User user = new User();
        var user = new User(); //자바 11버전부터 추가, 타입을 추론하여 축약됨
        user.setName("Kim Hyerin");
        user.setAddress("달리는 공룡");
        return user;
    }
}
