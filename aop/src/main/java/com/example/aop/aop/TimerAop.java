package com.example.aop.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component //Bean같은 경우 클래스에 붙일 수 없다
public class TimerAop {

    //포인트컷이란
    //내가 어느 부분에 AOP를 적용시킬건지 적용하는 것(대표적인 것 하나 살펴보자)
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //* com.example.aop 프로젝트에 controller 패키지 하위에 있는 모든 메소드를 AOP로 보겠다
    private void cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)") //패키지 하위에 있는 Timer라는 annotation에 제약을 걸 것이다
    private void enableTimer(){}

    @Around("cut() && enableTimer()") //위 두가지 조건 같이 쓸 것이다
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed(); //joinPoint의 proceed(이외의 어떤 메소드가 실행되고 나서)메소드가 실행되고 나서

        stopWatch.stop(); //타이머 종료
        System.out.println("total time : "+stopWatch.getTotalTimeSeconds()); //메소드가 얼마나 걸리는지 확인하기
    }

}
