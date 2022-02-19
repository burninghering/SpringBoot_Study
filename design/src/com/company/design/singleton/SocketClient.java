package com.company.design.singleton;

public class SocketClient {

    private static SocketClient socketClient = null; //자기 자신을 객체로 가지고 있어야 함

    public SocketClient(){} //기본 생성자로 생성자를 만들 수 없도록 private로 막아줌

    public static SocketClient getInstance(){ //생성되어있는 객체를 가지고 오거나, 객체가 없는 경우 새로 객체를 만들어줌
        //static은 여러 인스턴스에서 공통으로 사용하는 것

        if (socketClient==null){
            socketClient=new SocketClient();
        }
        return socketClient;
    }

    public void connect(){
        System.out.println("connect");
    }
}
