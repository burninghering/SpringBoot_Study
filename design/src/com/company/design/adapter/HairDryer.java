package com.company.design.adapter;

public class HairDryer implements Electronic110V{
    @Override //오버라이드 안하면 에러가 난다
    public void powerOn() {
        System.out.println("헤어드라이기 110v on");
    }
}
