package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.Usuario;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    //inyectamos nuestra dependencia a nivel de constructor
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional// esta anotacion lo que nos permite ver es que si hay algun error a nivel de "savetransactional"
    //esta anotacion permite hacer un rollback de todas las transacciones que se han ido registrando
    public void saveTransactional(List<Usuario> usuarios ){
        usuarios.stream()//el peek es para ir moen pantalla lo que se va registrando
                .peek(usuario ->  LOG.info("usuario insertado: " + usuario))
                .forEach(userRepository::save);
    }

    public List<Usuario> getAllUsers(){
         return userRepository.findAll();
    }

    public Usuario save(Usuario newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new Usuario(id));
    }

    public Usuario update(Usuario newUser, Long id) {
        return
                userRepository.findById(id)
                .map(
                        usuario -> {
                            usuario.setEmail(newUser.getEmail());
                            usuario.setBirthDate(newUser.getBirthDate());
                            usuario.setName(newUser.getName());
                            return userRepository.save(usuario);
                        }
                ).get();
    }
}
