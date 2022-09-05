package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    //https://openapi.naver.com/v1/search/local.json
    // ?query=\xEA\xB3\xB5\xEB\xA3\xA1
    // &display=10
    // &start=1
    // &sort=random
    @GetMapping("/naver")
    public String naver() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "순두부찌개")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        log.info("uri : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        //헤더를 사용하기 위해
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "TNYycpp5NSwKwDC0xjpg")
                .header("X-Naver-Client-Secret", "idHLtS94gI")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();

    }

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setAge(age); //echo 방식으로 동작할 것이기 때문에 age, name 넣자
        user.setName(name);
        return user;
    }

    //서버에서 헤더를 받을 준비를 하자~!
    @PostMapping("/user/{userId}/name/{userName}") //변경되는 값을 변수로 매칭해주자 (아래 @PathVariable로)
    public Req<User> post(
            //HttpEntity<String> entity, //클라이언트가 내게 뭘 보내온지 모르겠다면, HttpEntity로
            @RequestBody Req<User> user,//사용자가 보내온 -> @RequestBody로 User를 받는다
            @PathVariable int userId,
            @PathVariable String userName,
            @RequestHeader("x-authorization") String authorization, //괄호 안에 이름 매칭해준 것임
            @RequestHeader("custom-header") String customHeader
    ) {
        //log.info("req : {}",entity.getBody()); //getBody를 해보면 된다
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authorization : {}, custom : {}", authorization, customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>(); //response 만들어서
        response.setHeader(
                new Req.Header() //Header에 빈 값을 넣어준다
        );
        response.setrBody(user.getrBody()); //body에는 사용자가 보내왔던 user에서 getBody꺼내서

        return response; //user라는 클래스를 echo로 내리겠다
    }
}
