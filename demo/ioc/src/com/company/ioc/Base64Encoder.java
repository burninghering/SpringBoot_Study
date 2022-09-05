package com.company.ioc;

import java.util.Base64;

public class Base64Encoder implements IEncoder {

    public String encode(String message) { //메세지를 하나 받아서
        return Base64.getEncoder().encodeToString(message.getBytes()); //메세지를 getBytes로 리턴
    }
}
