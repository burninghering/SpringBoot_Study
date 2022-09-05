package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index); //해당 타입에 대해 옵셔널하게 값을 리턴해주는 메소드

    T save(T entity); //저장하는 메소드

    void deleteById(int index); //삭제

    List<T> listAll(); //리스트 메소드
}
