package com.informatorio.CarritoCompra.Controller;

import com.informatorio.CarritoCompra.Entity.User;
import com.informatorio.CarritoCompra.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value="/user")
    public User createUser(@RequestBody User entity) {
        return userRepository.save(entity);
    }
    
    @DeleteMapping(value="/user/{id}")
    public User putUser(@PathVariable("id") Long id, @RequestBody User user) {
        User userExist = userRepository.findById(id).get();
        userExist.setNombre(user.getNombre());
        userExist.setApellido(user.getApellido());
        userExist.setDireccion(user.getDireccion());
        return userRepository.save(userExist);
    }

    @PutMapping(value="/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "Usuario"+id+"Borrado";
    }

    @GetMapping(value="/user/{id}")
    public User getUserId(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }

    @GetMapping(value="/user")
    public ResponseEntity<?> getUserAll() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

}
