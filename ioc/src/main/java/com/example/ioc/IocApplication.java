package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args); //(1)스프링 어플리케이션이 실행되고 나면,

        ApplicationContext context = ApplicationContextProvider.getContext(); //(2)가져오자

        //(3)Bean을 찾는 방법은 이름/클래스 타입 등 여러가지 방법이 있다

        //(4)DI는 해줄거지만 IOC 즉 객체관리는 해주지 않을 것이다 (new로 안할 것이다)

        //더 이상 불러올 필요 없으니 주석처리한다!
        //Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        //UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        Encoder encoder = context.getBean("urlEncode", Encoder.class); //이름으로 찾아주기

        String url = "www.navaer.com/books/it?page=10&size=20&name=spring-boot";

        String result = encoder.encode(url);
        System.out.println(result);
    }

}

@Configuration //한 개의 클래스에서 여러 개의 Bean을 등록할 것이라는 의미
class AppConfig{

    @Bean("base64Encoder") //아까는 74여서 부딪힐 일 없음
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode") //UrlEncoder는 이미 쓰고 있기 때문에 Encode
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}
