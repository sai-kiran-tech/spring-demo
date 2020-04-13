package com.saikiran.springdemo.user;

import com.saikiran.springdemo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {

    @Autowired
    private UserDAOService service;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return service.finadAll();
    }

    @GetMapping("/users/{id}")
    public HttpEntity<User> getUserById(@PathVariable int id){

        User user= service.findById(id);

        if (user==null){
            throw new UserNotFoundException("id-"+id);
        }

        user.add(
                linkTo(methodOn(UserController.class)
                        .getAllUsers())
                        .withRel("Get-All-Users"));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser( @Valid @RequestBody User user){

        User newUser=service.add(user);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){

        User user=service.deleteById(id);

        if(user==null){

            throw new UserNotFoundException("id-"+id);
        }


        return ResponseEntity.noContent().build();
    }
}
