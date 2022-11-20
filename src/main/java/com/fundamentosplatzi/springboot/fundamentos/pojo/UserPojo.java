package com.fundamentosplatzi.springboot.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "user")//hay que configurar esta clase en la configuracion general, para poder inyectarla como dependencia
public class UserPojo {
    private String email;
    private String password;
    private int age;

    public UserPojo(String password,String email, int age) {
        this.email = email;
        this.password = password;
        this.age = age;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
