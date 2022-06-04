package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @GetMapping("")
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        //(required = false)란 그 변수가 없어도 동작을 하되, 그 변수는 Null이 된다
        //(required = false)는 ?name=1234같이 값이 안들어가있으면 error 터뜨림
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a= 10+age; //age에 값을 넣어주지 않으면 Null point가 발생하도록 함

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){

        System.out.println("api controller");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
