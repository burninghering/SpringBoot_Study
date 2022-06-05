package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class GlobalControllerAdvice {

    //내가 잡고자 하는 메소드 만들어보자

    @ExceptionHandler(value = Exception.class) //전체적인 예외 다 잡을거임
    public ResponseEntity exception(Exception e){ //Rest API니까 ResponseEntity받자
        //value = Exception.class 여기서 설정한 값을 Exception e에 매개변수로 받을 수 있다

        System.out.println(e.getClass().getName());
        System.out.println("----------------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("----------------------");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");

    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
