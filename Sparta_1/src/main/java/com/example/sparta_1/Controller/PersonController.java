package com.example.sparta_1.Controller;

import com.example.sparta_1.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //- 클라이언트의 요청(Request)을 전달받는 코드를 Controller 라고 부릅니다.
//→ JSON 만을 돌려주는 것은 RestController
public class PersonController {

    @GetMapping("/person")
    public Person personController(){
        Person person = new Person();
        person.setAge(26);
        person.setJob("Programmer");
        person.setName("Hyerin");
        return person;
    }
}
