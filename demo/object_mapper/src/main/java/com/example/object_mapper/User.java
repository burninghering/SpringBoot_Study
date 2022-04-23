package com.example.object_mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String name;
    private int age;

    @JsonProperty("phone_number")
    private String phoneNumber;

    public User(){ //(2) 예시의 기본 생성자 추가해줌
        this.name=null;
        this.age=0;
        this.phoneNumber=null;
    }

    public User(String name, int age,String phoneNumber){ //생성자 오버로딩
        this.name=name;
        this.age=age;
        this.phoneNumber=phoneNumber;
    }

    public User getDefaultUser(){ //특정 클래스에 get 메소드가 아니라 이런 메소드를 만들 때도 있음
        return new User("defult",0,"010-1111-2222"); //클래스 이름에 get을 붙여버리면 object mapper가 착각하므로,
        //내가 object mapper를 활용하려면 클래스에 get이 붙은 메소드를 만들면 안된다.
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
