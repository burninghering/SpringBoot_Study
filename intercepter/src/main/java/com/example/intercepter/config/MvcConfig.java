package com.example.intercepter.config;

import com.example.intercepter.intercepter.AuthInterception;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor //final로 선언된 객체들을 생성자에서 주입받을 수 있도록 해준다
public class MvcConfig implements WebMvcConfigurer {

    //@Autowired로 자기자신을 받을 수 있지만 순환 참조 생길까봐 @RequiredArgsConstructor 사용해서 생성자에서 주입받도록 함
    private final AuthInterception authInterception;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterception).addPathPatterns("/api/private/*"); //"/api/private/*" 하위 아래 모든것만 검사하겠다.
//        registy.addInterceptor... 위의 코드가 끝나면 이 코드와 같이 다른 인증도 할 수 있음
    }
}
