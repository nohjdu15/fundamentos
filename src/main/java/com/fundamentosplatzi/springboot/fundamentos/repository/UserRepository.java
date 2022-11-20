package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UsuarioDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
//jparepositopry recibe como parametros 1-la entidad que queremos mapear y 2 tipo de dato del ÏD de dicha entidad
public interface UserRepository extends JpaRepository<Usuario,  Long> {

    @Query("Select u from Usuario u WHERE u.email=?1")
    Optional<Usuario> findByUserEmail(String email);

    @Query("Select u from Usuario u WHERE u.name like ?1%")
    List<Usuario> findAndSort(String name, Sort sort);
    @Query("Select u from Usuario u WHERE u.name like ?1%")
    List<Usuario> findByName(String name);
    @Query("select u from  Usuario u WHERE u.name like ?1% and u.email like ?2%")
    Optional<Usuario> findByNameAndEmail(String name, String email);

    List<Usuario> findByNameLike(String name);

    List<Usuario> findByNameOrEmail(String name, String email);

    List<Usuario> findByBirthDateBetween(LocalDate begin, LocalDate end);

    @Query("Select u from Usuario u WHERE u.name like ?1 order by u.name desc" )
    List<Usuario> findByNameContainingOrderByDesc(String name);

    //sentencia de tipo JPQL
    //En esta sentencia utilizamos dto para representar los datos que vamos a retribuir de nuestra base de datos
    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UsuarioDto(u.id, u.name, u.birthDate)" +
            " FROM Usuario u " +
            " Where u.birthDate=:parametroFecha " +
            " and u.email=:parametroEmail ")
    Optional<UsuarioDto> getAllByBirthDateAndEmail(@Param("parametroFecha")LocalDate date,
                                                   @Param("parametroEmail") String email);
}
