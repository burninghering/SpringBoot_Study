package com.example.sparta_1.domain;

import com.example.sparta_1.models.PersonRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String name;

    public Person(int age, String name) {
        this.age=age;
        this.name=name;
    }

    public void update(PersonRequestDto personRequestDto){
        this.age=personRequestDto.getAge();
        this.name=personRequestDto.getName();
    }

}
