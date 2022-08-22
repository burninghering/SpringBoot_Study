package com.example.client.service;

import lombok.extern.slf4j.Slf4j;
import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Slf4j
@Service
public class RestTemplateService {

    //http://localhost/api/server/hello
    //response

    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","Hyerin")
                .queryParam("age",26)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri,UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public void post(){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        System.out.println(uri);

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(uri,req,String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        //return response.getBody(); 없애버렷
    }

    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        System.out.println(uri);

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);


        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri) //post로 uri 넣을 것
                .contentType(MediaType.APPLICATION_JSON) //미디어 타입은 json으로
                .header("x-authorization","abcd") //header 내용
                .header("custom-header","fffff") //계속해서 이렇게 값을 넣어도 된다
                .body(req); //request body 내용 -> 위에서 만든 오브젝트 넣을 것임

        RestTemplate restTemplate = new RestTemplate();

        //호출하기
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity,UserResponse.class); //헤더 싣고 보냄
        return response.getBody();
    }



    public Req<UserResponse> genericExchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        System.out.println(uri);

        // http body->object->object mapper->json->rest template->http body json

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<>(); //Req 클래스의 body는 제네릭 타입이기 때문에 <UserRequest>라고 지정
        req.setHeader(new Req.Header());
        req.setrBody(userRequest);

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity //Req가 한 번 감싸는 것으로 한다
                .post(uri) //post로 uri 넣을 것
                .contentType(MediaType.APPLICATION_JSON) //미디어 타입은 json으로
                .header("x-authorization","abcd") //header 내용
                .header("custom-header","fffff") //계속해서 이렇게 값을 넣어도 된다
                .body(req); //request body 내용 -> 위에서 만든 오브젝트 넣을 것임

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response = restTemplate.exchange(requestEntity,new ParameterizedTypeReference<Req<UserResponse>>(){});
        //requestEntity보낼 것이고, 우리가 원하는 응답은 Req<UserResponse>.class이지만 제네릭에는 class를 붙일 수 없다
        //이것에 대응하기 위한 코드로 new ParameterizedTypeReference<Req<UserResponse>>(){}; 를 넣어주자

        return response.getBody();
        //처음 getBody()는 ResponseEntity의 body를 가져온 것
        //두 번째 getBody()는 그 안에 들어있는 Req<UserResponse>, Req 클래스에 지정해놓은 private T rBody
    }
}
