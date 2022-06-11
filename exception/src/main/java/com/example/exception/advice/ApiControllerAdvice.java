package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;

@RestControllerAdvice(basePackageClasses = ApiController.class) //ApiController에서만 작동하게 된다
public class ApiControllerAdvice { ////ApiController에서만 작동하게 되니까 Global에서 Api로 바꿔줌

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName()); //어떤 클래스에서 에러가 났는지 확인!
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class) //인자가 없을 때 발생하는 에러 잡기
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){

        BindingResult bindingResult = e.getBindingResult();

        bindingResult.getAllErrors().forEach(error->{

            FieldError field = (FieldError) error; //형변환

            String fieldName=field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            System.out.println("---------------");
            System.out.println(fieldName);
            System.out.println(message);
            System.out.println(value);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value= ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e){
        //어떠한 필드가 잘못되었을때의 정보를 담고 있음
        e.getConstraintViolations().forEach(error ->{

            String field=error.getPropertyPath().spliterator()
            String message=error.getMessage();
            String value=error.getInvalidValue().toString();

            System.out.println("---------------");
            System.out.println(field);
            System.out.println(message);
            System.out.println(value);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value= MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e){

        String fieldName=e.getParameterName();
        String fieldType=e.getParameterType();
        String invalidValue=e.getMessage();

        System.out.println(fieldName);
        System.out.println(fieldType);
        System.out.println(invalidValue);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
