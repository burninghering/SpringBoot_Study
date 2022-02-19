package com.company.design.singleton;

public class Bclazz {
    private SocketClient socketClient;

    public Bclazz(){
        //this.socketClient=SocketClient.getInstance();
        this.socketClient=new SocketClient(); ////Bclazz에서 자기가 직접 객체를 생성
    }

    public SocketClient getSocketClient(){
        return this.socketClient;
    }
}
