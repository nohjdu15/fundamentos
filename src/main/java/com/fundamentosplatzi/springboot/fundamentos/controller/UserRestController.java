package com.fundamentosplatzi.springboot.fundamentos.controller;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.Usuario;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//aqui van todos los servicios REST que son consumidos por un cliente
@RestController
@RequestMapping("/api/users")// esta sera la ruta por la cual sera consumido  el servicio get
//permite que todos los metodos que heredemos aca se formateen en formato json
public class UserRestController {
    //vamos a tener servicios para create, get, update, delete

     private GetUser getUser;
     private CreateUser createUser;
     //inyectamos el delete
    private DeleteUser deleteUser;
    //inyectamos el update
    private UpdateUser updateUser;


    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }
    //el consumo va a ser de tipo getmapping
    @GetMapping("/") //Tambien hay que indicar por donde va a ser consumido a nivel http
    //aca tambien hay que indicar por donde va a ser consumnido a nivel http
    @ResponseBody
    List<Usuario> get(){
        return getUser.getAll();
    }
    //aqui creamos el metodo post
    @PostMapping("/")
    ResponseEntity<Usuario> newUser(@RequestBody Usuario newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }
    //aqui creamos el metodo DELETE

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<Usuario> replaceUser(@RequestBody Usuario newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);

    }



}
