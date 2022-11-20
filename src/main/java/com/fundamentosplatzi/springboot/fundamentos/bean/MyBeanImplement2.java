package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanImplement2 implements MyBean {
    @Override//sobrescribir
    public void print() {
        System.out.println("hola desde mi propia implementacion de my bean 2");
    }
}
