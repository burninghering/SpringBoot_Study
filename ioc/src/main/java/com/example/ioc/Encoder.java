package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Encoder {

    private IEncoder iEncoder; //외부에서 주입받음 -> Dependancy Injection을 받은 것이다

    public Encoder(IEncoder iEncoder){
        this.iEncoder=iEncoder;
    }

    //Bean을 주입받을 수 있는 방법은
    //변수 / 생성자 / set 메소드

    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder=iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
