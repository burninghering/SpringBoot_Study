package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db =new ArrayList<>();
    private int index=0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex()==index).findFirst(); //<T extends MemoryDbEntity> 제네릭 타입에 와일드카드?를 걸었기 때문에
        //MemoryDbEntity를 상속받은 객체라면, getIndex()를 통해 db에서 해당 인덱스에 해당하는 것을 찾아 옵셔널하게 리턴하게 됨(있을 수도, 없을 수도 있는 데이터)
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it->it.getIndex()==entity.getIndex()).findFirst();
        if(optionalEntity.isEmpty()){//db에 데이터가 없는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }else{
            //db에 이미 데이터가 있는 경우
            var preIndex=optionalEntity.get().getIndex(); //사전에 있던 데이터를 가져와서
            entity.setIndex(preIndex); //사전에 있던 데이터를 업데이트

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it->it.getIndex()==index).findFirst();
        if(optionalEntity.isPresent()){ //데이터가 이미 있는 경우에는
            db.remove(optionalEntity.get());

        }
    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
