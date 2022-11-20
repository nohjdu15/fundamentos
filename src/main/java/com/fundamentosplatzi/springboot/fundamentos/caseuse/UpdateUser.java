package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.Usuario;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;


    @Component
    public class UpdateUser {
        private UserService userService;

        public UpdateUser(UserService userService){
            this.userService = userService;
        }

        //aqui la funcion es de tipo entidad por eso es "Usuario"
        // y su respuesta o retorno sera de tipo entidad
        public Usuario update(Usuario newUser, Long id) {
            return userService.update(newUser, id);
        }
    }
