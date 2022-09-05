package com.company.ioc;

public class Encoder {

    private IEncoder iEncoder; //외부에서 주입받음 -> Dependancy Injection을 받은 것이다

    public Encoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
