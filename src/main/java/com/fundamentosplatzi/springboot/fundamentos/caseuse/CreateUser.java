package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.Usuario;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;


@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService){
        this.userService = userService;
    }

    public Usuario save(Usuario newUser) {
        return userService.save(newUser);
    }
}
