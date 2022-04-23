package com.example.object_mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("---------------------");

        var objectMapper = new ObjectMapper(); //직접 객체로 만들어서 활용해보자

        // (1) object->TEXT
        var user = new User("steve",10,"010-1111-2222"); //생성자 오버로딩으로 객체 초기화
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);
        //실행 -> 에러남
        //object->TEXT 할 때, object mapper는 get 메소드를 활용한다. -> User 클래스에 getter 메소드 만들어줌


        // (2) TEXT->object
        var objectUser = objectMapper.readValue(text, User.class); //인자에 처음엔 Json 넣어주고, 어떤 클래스 오브젝트를 만들것인지 넣어줌
        System.out.println(objectUser);
        //실행 -> 에러남
        //생성자 오버로딩을 했기 때문에 현재 기본 생성자가 없다.
        //TEXT 형태의 JSON -> Object 할 때, object mapper를 실행하려면 기본 생성자가 있어야 한다.
    }

}
