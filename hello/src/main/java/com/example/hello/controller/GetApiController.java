package com.example.hello.controller;

import com.example.hello.DTO.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path="/hello") //http:localhost:8080/api/get/hello
    public String hello(){
        return "get Hello";
    }

    @RequestMapping(path="/hi", method= RequestMethod.GET) //get http://localhost:8080/api/get/hi
    public String hi(){
        return "hi";
    }

    //http:localhost:8080/api/get/path-variable/{name} <-변화하는 값을 넣자
    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable(name="id") String pathName){ //@PathVariable(name=" ") 안에다가 선언하자
        System.out.println("PathVariable : "+pathName);
        return pathName;
    }

    //https://www.google.com/search?q = bts  <==처음에는 ?로 시작해서 key value가 들어가고,
    // &oq=bts <== 그 다음 key value가 오기 위해서는 & 연산자가 필요한 것이다.
    // &aqs=chrome..69i57j46i67l2j0i67l2j69i61j69i60l2.3003j0j7
    // &sourceid=chrome
    // &ie=UTF-8

    //?key=value&key2=value2

    //q= 에서 시작하는 부분이 query parameter
    //검색을 할 때, 여러 가지 매개 변수 인자를 뜻함

    //http://localhost:8080/api/get/query-param?user=steve&email=steve@gmail.com&age=30

    @GetMapping(path="query-param")
    public String queryParam(@RequestParam Map<String,String> queryParam){ //Map으로 받는 경우에는 key값을 알 수 없음

        StringBuilder sb = new StringBuilder(); //String을 붙일 때 메모리 낭비를 막기위해 사용

        queryParam.entrySet().forEach(entry->{ //entrySet -> MAP 전체 출력 시 Key,Value값 모두 가져올 때 사용
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");


            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });

        return sb.toString(); //객체를 문자화하여 나타내기
    }

    //Map으로 받을 때는 모든 키를 다 받을 수 있지만,
    //코딩할 때 불편한 점 : queryParam.get("name") 이렇게 name을 얻는 방식으로 하나하나 다 지정해줘야 한다.
    //그것이 아니라 명시적으로 변수로 받기 위해서는 @RequestParam을 각 변수 앞에 다 붙여주면 된다.

    //QueryParameter에 넣을 수 있는 것은, user/email/age라고 딱 선언해주는 방법
    @GetMapping("query-param02")
    public String queryParam02(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam int age
        ){

        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }


    //userRequest 객체가 들어오면,
    //queryparameter에 들어있는 주소들, 즉 물음표 뒤에 있는 것들을 스프링이 판단한다
    //?user=steve&email=steve@gmail.com&age=30
    //그리고 key에 해당하는 것들을 (user,email,age) 해당 객체에서 변수와 매칭을 해주게 된다.
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){ //객체를 활용해서 받는 것이 아주 편하다!

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
