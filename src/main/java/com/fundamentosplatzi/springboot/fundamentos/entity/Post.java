package com.fundamentosplatzi.springboot.fundamentos.entity;

import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    //esta clase debe tener siempre un ID, este representa la entidad a nivel de la tabla en la db
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable=false, unique = true)
//Representamos la columna de la base de datos como una propiedad a nivel de clase
    private Long id; // tipo de dato Long y el nombre de la columna sera id
    @Column(name="description", length= 255)
    private String description;

//ESTA ES LA RELACION CON LA TABLA USUARIO
    @ManyToOne
    private Usuario usuario;

    public Post() {
    }

    public Post(String description, Usuario usuario) {
        this.description = description;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario user) {
        this.usuario = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + usuario +
                '}';
    }
}
