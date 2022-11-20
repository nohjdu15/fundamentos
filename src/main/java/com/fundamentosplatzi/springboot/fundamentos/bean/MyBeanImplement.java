package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanImplement implements MyBean {
    @Override//sobrescribir
    public void print() {
        System.out.println("hola desde mi propia implementacion de my bean");
    }
}
