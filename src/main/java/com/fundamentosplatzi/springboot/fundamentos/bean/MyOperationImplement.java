package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyOperationImplement implements MyOperation {//esto seria un Bean
    @Override
    public int suma(int number) {
        return number+1;
    }
}
