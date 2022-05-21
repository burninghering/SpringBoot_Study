package com.example.ioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    //(1)Web으로부터 주입을 받는데, 스프링이 주입을 해준다
    private static ApplicationContext context;

    @Override //(2)set 메소드를 만들때 ApplicationContext를 주입해줄 것이며 우리는 그것을 받아 static 변수에 할당할 것이다(위에)
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }

    public static ApplicationContext getContext(){ //(3)그럼 우리는 가져다 쓰기만 하면 된다!
        return context;
    }
}
