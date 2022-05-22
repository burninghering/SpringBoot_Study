package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect //AOP로 작동시키기
@Component //스프링이 관리하게 하기
public class ParameterAop {

    //포인트컷이란
    //내가 어느 부분에 적용시킬건지 적용하는 것(대표적인 것 하나 살펴보자)
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //* com.example.aop 프로젝트에 controller 패키지 하위에 있는 모든 메소드를 aop로 보겠다
    private void cut(){ //메소드명 아무거나 가능

    }

    //메소드 실행되기 전 어떠한 값이 들어가는지 확인
    //언제 실행시킬 것이냐?
    @Before("cut()") //cut 메소드가 실행되는 시점에 before를 실행시키겠다
    public void before(JoinPoint joinPoint){ //들어가는 지점에 대한 정보를 가지고 있는 joinpoint

        //**새로 추가된 부분
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs(); //메소드에 들어가고 있는 매개변수들의 배열
        for(Object obj : args){ //매개변수 값 잘 찍히는지 확인해보기
            System.out.println("type "+obj.getClass().getSimpleName()); //타입 가져오기
            System.out.println("value "+obj);
        }
    }

    //들어간 후 어떠한 값이 리턴되는지
    //반환값이 뭘까?
    @AfterReturning(value = "cut()", returning = "returnobj") //returning="" 안에 내가 받고싶은 오브젝트 객체 이름 넣어주기
    public void afterReturn(JoinPoint joinPoint, Object returnobj){ //returning값과 매칭되어야함
        System.out.println("return obj");
        System.out.println(returnobj);
    }

}
