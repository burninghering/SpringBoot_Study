package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class DecodeAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode(){}

    //전은 디코딩을 해서 내보낼 것이고,
    //후는 엔코딩을 해서 내보낼 것이다.

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs(); //메소드가 돌 때, 메소드의 파라미터들 중에

        for(Object arg : args){
            if(arg instanceof User) {//argument의 instance가 우리가 만들어 놓은 User라면
            //내가 원하는 User라는 객체를 찾았으면 값을 바꿔준다
                User user = User.class.cast(arg); //User라는 class로 형변환 시키기
                String base64Email=user.getEmail(); //기존에 encoding되어있던 email을 꺼냅니다
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8"); //decoding을 해준다다                user.setEmail(email); //decoding된 email을 다시 set 해줌

            //실질적인 controller 코드에서는 user를 decode할 일은 없어진다
            //이러한 기능들을 enableDecode() 어노테이션을 통해 할 것이다
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()",returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint,Object returnObj){ //오브젝트에서
        if(returnObj instanceof User) { //User를 찾아서
            User user = User.class.cast(returnObj);

            String email = user.getEmail(); //평문 email을
            String base64Email = Base64.getDecoder().encodeToString(email.getBytes()); //encoding
            user.setEmail(base64Email); //base64Email로 encoding해서 내려주기
        }
    }
}
