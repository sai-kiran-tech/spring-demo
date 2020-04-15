package com.saikiran.springdemo.user;

import com.saikiran.springdemo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserDAOService service;

    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public HttpEntity<User> getUserById(@PathVariable int id){

        Optional<User> optionUser= userRepository.findById(id);

        if (!optionUser.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }

        User user= optionUser.get();

        user.add(
                linkTo(methodOn(UserJpaController.class)
                        .getAllUsers())
                        .withRel("Get-All-Users"));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser( @Valid @RequestBody User user){

        User newUser=userRepository.save(user);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){

        userRepository.deleteById(id);

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id){

        Optional<User> optionUser= userRepository.findById(id);

        if (!optionUser.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }

        return optionUser.get().getPosts();

    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){

        Optional<User> optionUser= userRepository.findById(id);

        if (!optionUser.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }

        User user= optionUser.get();
        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
