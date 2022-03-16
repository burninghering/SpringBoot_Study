package com.example.hello.controller;

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

    @GetMapping("path=/query-param")
    public String queryParam(@RequestParam Map<String,String> queryParam){

        StringBuilder sb = new StringBuilder(); //String을 붙일 때 메모리 낭비를 막기위해 사용

        queryParam.entrySet().forEach(entry->{ //entrySet -> MAP 전체 출력 시 Key,Value값 모두 가져올 때 사용
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue());
        });

        return sb.toString(); //객체를 문자화하여 나타내기
    }


}
