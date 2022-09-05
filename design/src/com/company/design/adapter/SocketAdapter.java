package com.company.design.adapter;

import com.company.design.singleton.SocketClient;

public class SocketAdapter implements Electronic110V { //110V->220V

    private Electronic220V electronic220V; //자기 자신을 연결시켜줄 220V를 가지고 있어야 함

    public SocketAdapter(Electronic220V electronic220V) { //생성자로 220V를 받자
        this.electronic220V = electronic220V;
    }

    @Override
    public void powerOn() { //이 함수를 통해 110V가 220V로 바뀌게 됨!
        electronic220V.connect();
    }
}
