package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.Usuario;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {
//llamamos a la dependencia de tipo userService
    private UserService userService;
    //inyectamos la dependencias userService a partir de su metodo constructor
    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Usuario> getAll() {
        return userService.getAllUsers();
    }
}
