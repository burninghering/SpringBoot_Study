package com.company.design;

import com.company.design.adapter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.decorator.*;
import com.company.design.facade.Ftp;
import com.company.design.facade.Reader;
import com.company.design.facade.SftpClient;
import com.company.design.facade.Writer;
import com.company.design.observer.Button;
import com.company.design.observer.IButtonListener;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.Aclazz;
import com.company.design.singleton.Bclazz;
import com.company.design.singleton.SocketClient;
import com.company.design.strategy.*;

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

        //Decorator start->
//        ICar audi=new Audi(1000);
//        audi.showPrice();
//
//        //a3
//        ICar audi3 = new A3(audi,"A3");
//        audi3.showPrice();
//        //a4
//        ICar audi4 = new A4(audi,"A4");
//        audi4.showPrice();
//        //a5
//        ICar audi5 = new A5(audi,"A5");
//        audi5.showPrice();
        //<-end Decorator

        //start Observer->
//        Button button = new Button("버튼");
//
//        button.addListener(new IButtonListener() {
//            @Override
//            public void clickEvent(String event) {
//                System.out.println(event);
//            }
//        });
//
//        button.click("메시지 전달 : click 1");
//        button.click("메시지 전달 : click 2");
//        button.click("메시지 전달 : click 3");
//        button.click("메시지 전달 : click 4");
        //<-end Observer

//        Ftp ftpClient = new Ftp("www.foo.co.kr",22,"/home/etc");
//        ftpClient.connect();
//        ftpClient.moveDirectory();
//
//        Writer writer = new Writer("text.tmp");
//        writer.fileConnect();
//        writer.write();
//
//        Reader reader = new Reader("text.tmp");
//        reader.fileConnect();
//        reader.fileRead();
//
//        reader.fileDisconnect();
//        writer.fileDisconnect();
//        ftpClient.disConnect();
//        //위 예시는 굉장히 추상화된 형태이지만, 각각의 객체에 너무 의존하고 있다.
//
//        //그러므로 아래와 같이 파사드 패턴을 적용시키면,
//        //앞 쪽 정면만 바라볼 수 있도록 객체 하나 만들 수 있고,
//        //여러가지 의존성을 가진 것을 새로운 인터페이스 형태로 제공한다.
//        SftpClient sftpClient = new SftpClient("www.foo.co.kr",22,"/home/etc","text.tmp");
//        sftpClient.connect();
//        sftpClient.write();
//        sftpClient.read();
//        sftpClient.disConnect();

        Encoder encoder = new Encoder(); //전략을 사용하기 위한 원본 객체(변하지 않음)

        //base64
        EncodingStrategy base64 = new Base64Strategy(); //전략 생성

        //normal
        EncodingStrategy normal = new NomalStrategy(); //전략 생성


        String message = "Hello java";

        encoder.setEncodingStrategy(base64); //전략 사용할 때마다 세팅
        String base64Result = encoder.getMessage(message);
        System.out.println(base64Result);

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);
        System.out.println(normalResult);

        encoder.setEncodingStrategy(new AppendStrategy());
        String appendResult = encoder.getMessage(message);
        System.out.println(appendResult);
        //전략을 주입함으로써 결과가 달라지도록 함함
    }

    //adapter : 콘센트
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
