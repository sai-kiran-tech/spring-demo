package com.saikiran.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAOService service;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return service.finadAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id){

        return service.findById(id);
    }
}
