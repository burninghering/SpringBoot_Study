package com.company.design.singleton;

public class Aclazz {

    private SocketClient socketClient;

    public Aclazz() {
        //    this.socketClient=SocketClient.getInstance(); //기본 생성자를 막아놨으므로, getInstance()함수를 통해 만들어야 함
        this.socketClient = new SocketClient(); //Aclazz에서 자기가 직접 객체를 생성
    }

    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}
