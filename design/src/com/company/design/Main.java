package com.company.design;

import com.company.design.adapter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.decorator.*;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.Aclazz;
import com.company.design.singleton.Bclazz;
import com.company.design.singleton.SocketClient;

import static java.awt.PageAttributes.MediaType.A4;

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
//        HairDryer hairDryer = new HairDryer();
//        connect(hairDryer);
//
//        Cleaner cleaner = new Cleaner();
//        Electronic110V adapter = new SocketAdapter(cleaner); //Electronic110V 인터페이스 타입을 가진 SocketAdapter의 인스턴스
//        connect(adapter);
//
//        AirConditioner airConditioner = new AirConditioner();
//        Electronic110V airAdater = new SocketAdapter(airConditioner);
//        connect(airAdater);
        //<-end adapter
        //proxy start->
//        Browser browser = new Browser("www.naver.com");
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show(); // 매번 네이버로부터 url을 받아오고 있음

//        IBrowser browser = new BrowserProxy("www.naver.com");
//        browser.show(); //처음에는 url을 네이버에서 받아오지만
//        browser.show(); //캐시 기능 사용해 불러오고 있다.
//        browser.show();
//        browser.show();
        //<-end proxy

        ICar audi=new Audi(1000);
        audi.showPrice();

        //a3
        ICar audi3 = new A3(audi,"A3");
        audi3.showPrice();
        //a4
        ICar audi4 = new A4(audi,"A4");
        audi4.showPrice();
        //a5
        ICar audi5 = new A5(audi,"A5");
        audi5.showPrice();
    }

    //adapter : 콘센트
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}
