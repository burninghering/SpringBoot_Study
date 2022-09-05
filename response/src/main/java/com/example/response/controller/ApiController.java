package com.example.response.controller;


import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    // TEXT
    @GetMapping("/text")
    public String test(@RequestParam String account) {
        return account;
    }

    //JSON
    //user라는 객체를 @RequestBody로 받아 User로 리턴한다.
    //request가 오면, object mapper를 통해 object로 바뀌게 된다.
    //그리고 아래 method를 타게 되고, response 나갈 때 object를 던진다.
    //그럼 도다시 object mapper를 통해 json으로 바뀌게 되어 response로 내려간다.
    //req->object mapper->object->method->object->object mapper->json->response
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user; // 200 ok
    }

    //put으로 생성되었다고 하면 201, 불러오면 200? 아무튼 200/201 둘다 내려옴
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        //ResponseEntity로 제네릭을 설정해줘서 response를 내려줄 때 status를 정해줄 것이다.

        return ResponseEntity.status(HttpStatus.CREATED).body(user); //생성 시 명확하게 201 코드내려주고 body 싣기
        // body에 user 데이터 보내는데 이것도 object mapper를 통해 json으로 바뀜

        //응답에 대한 커스터마이징이 필요하면, ResponseEntity를 사용하면 된다
    }
}

