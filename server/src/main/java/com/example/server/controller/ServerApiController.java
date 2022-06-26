package com.example.server.controller;

import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setAge(age); //echo 방식으로 동작할 것이기 때문에 age, name 넣자
        user.setName(name);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}") //변경되는 값을 변수로 매칭해주자 (아래 @PathVariable로)
    public User post(@RequestBody User user,@PathVariable int userId,@PathVariable String userName){ //@RequestBody로 User를 받는다
        log.info("client req : {}",user); //user의 toString이 괄호에 들어간다는 뜻

        log.info("userId : {}, userName : {}",userId,userName);

        return user;
    }
}
