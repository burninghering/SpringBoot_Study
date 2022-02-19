package com.company.design;

import com.company.design.adapter.*;
import com.company.design.singleton.Aclazz;
import com.company.design.singleton.Bclazz;
import com.company.design.singleton.SocketClient;

public class Main {

    public static void main(String[] args) {

        //singleton start->
//        Aclazz aclazz = new Aclazz();
//        Bclazz bclazz = new Bclazz();
//        SocketClient aClient = aclazz.getSocketClient();
//        SocketClient bClient = bclazz.getSocketClient();
//        System.out.println("두 개의 객체가 동일한가?");
//        System.out.println(aClient.equals(bClient));
        //<-end singleton

        //adapter start->
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner); //Electronic110V 인터페이스 타입을 가진 SocketAdapter의 인스턴스
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdater = new SocketAdapter(airConditioner);
        connect(airAdater);
    }

    //adapter : 콘센트
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}
