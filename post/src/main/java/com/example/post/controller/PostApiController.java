package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @PostMapping("/post")
    public void post(@RequestBody PostRequestDto requestData){ //키는 string, value는 object

        System.out.println(requestData);

        //후에, 특정 변수에 있는 데이터를 가져와서 데이터 처리하기
        requestData.getAccount();
    }
}
