package com.fundamentosplatzi.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
//CREAMOS LA ENTIDAD "user"
@Table(name= "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//aqui se autogenera un numero cada que tengamos un registro

    //AQUI VAN LAS COLUMNAS DE LA TABLA
    @Column(name = "id_user", nullable=false, unique = true)
//Representamos la columna de la base de datos como una propiedad a nivel de clase
    private Long id; // tipo de dato Long y el nombre de la columna sera id


    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String email;
    private LocalDate birthDate;//esta propiedad no tiene lengt porque es de tipo fecha
    //esta es la ralacion que tiene con la entidad POST

    //Aqui creamos la relacion con otra entidad de tipo OneToMany
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)//esta es la propiedad que representa a "user" en la entidad Post
    //@JsonManagedReference//esta anotacion sirve para cuando accedamos a este servicio no nos de un error de stackoverflow
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    public Usuario() {

    }
    //asi mismo sera el orden en las columnas
    public Usuario(String name,String email, LocalDate birthDate ) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Usuario(Long id){
        this.id = id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{ " +
                "id= " + id +
                ", name= '" + name + '\'' +
                ", email= '" + email + '\'' +
                ", birthDate= " + birthDate +
                ", posts= " + posts +
                '}';
    }
}
