package com.saikiran.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userDAOService.finadAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id){

        return userDAOService.findById(id);
    }
}
