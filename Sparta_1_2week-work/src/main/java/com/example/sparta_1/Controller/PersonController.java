package com.example.sparta_1.Controller;

import com.example.sparta_1.domain.Person;
import com.example.sparta_1.domain.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController //- 클라이언트의 요청(Request)을 전달받는 코드를 Controller 라고 부릅니다.
//→ JSON 만을 돌려주는 것은 RestController
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/api/persons")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

}
