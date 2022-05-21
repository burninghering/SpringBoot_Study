package com.example.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encoder") //나는 얘 이름을 base74Encoder로 쓸래
public class Base64Encoder implements IEncoder{

    public String encode(String message){ //메세지를 하나 받아서
        return Base64.getEncoder().encodeToString(message.getBytes()); //메세지를 getBytes로 리턴
    }
}
