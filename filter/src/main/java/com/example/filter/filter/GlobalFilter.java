package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
//@Component 삭제하고
@WebFilter(urlPatterns = "/api/user/*") //api/user/하위 모든 주소에 Filter를 매칭시키겠다는 뜻
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /////////////////들어가기 전, 전처리 구간

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
        //(ContentCaching를 생성했을 때 read하지 않고 일단 길이만 초기화시킴)

        chain.doFilter(httpServletRequest, httpServletResponse);
        //doFilter를 통해 실제 내부 스프링 안에 들어가야 그 메소드가 실행되어 컨텐츠가 아래 ByteArray에 들어갈것임.
        //그래서 핵심은? doFilter이후에 찍어야한다!

        String url = httpServletRequest.getRequestURI(); //그러니 이 코드도 doFilter 아래로 내리자

        /////////////////chain.doFilter 동작 후 response 생성됨, 후처리 구간
        //doFilter가 일어난 후 request에 대한 정보를 찍어보자
        String reqContent = new String(httpServletRequest.getContentAsByteArray()); //컨텐츠의 내용을 ByteArray로 받을것임
        log.info("requset url : {}, request body : {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray()); //여기서 한번 읽으며, Body의 커서 끝까지 내려가서 내용이 없는 상태.
        int httpStatus = httpServletResponse.getStatus();
        //그래서 읽은 만큼 복사를 해주자
        httpServletResponse.copyBodyToResponse(); //복사를 함으로써 Body를 채워준다

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);
    }
}
