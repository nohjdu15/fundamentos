package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    //Le pasamos la clase en la cual queremos mostrar el error
    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo printWhitDependency");
        int number = 5;
        LOGGER.debug("EL NUMERO ENVIADO COMO PARAMETRO A LA DEPENDENCIA ES : " + number);
        System.out.println("jajaja " + myOperation.suma(number));
        System.out.println("hola desde la implementacion de un bean con dependencia");
    }
}
