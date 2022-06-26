package com.example.intercepter.intercepter;

import com.example.intercepter.annotation.Auth;
import com.example.intercepter.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

    @Slf4j
    @Component
    public class AuthInterception implements HandlerInterceptor { //HandlerInterceptor 상속받기
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String url = request.getRequestURI();
            log.info("request url : {}", url);

            boolean hasAnnotation = checkAnnotation(handler, Auth.class); //스프링 context에서 관리중 (Filter는 이게 불가능)
            log.info("has annotation : {}", hasAnnotation);

            URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                    .query(request.getQueryString())
                    .build()
                    .toUri();
            if(hasAnnotation){

                String query = uri.getQuery();
                log.info("query : {}", query);
                if(query.equals("name=steve")){ //쿼리가 steve일때만 통과
                    return true;
                }
                //권한이 없으면
//                return false; 가 아니라
                throw new AuthException();
            }

            return true;
        }

    private boolean checkAnnotation(Object handler, Class clazz){

        //resource javascript, html
        if (handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler; //handlerMethod 객체를 만들기 위해 handler를 형변화시켰다

        if(null !=handlerMethod.getMethodAnnotation(clazz) || null!=handlerMethod.getBeanType().getAnnotation(clazz)){ //MethodAnnotation에 class가 붙어있는가 or annotation이 달려있는가
            // Auth annotation이 있으면 무조건 true
            return true;
        }
    return false;
    }
}
