package com.saikiran.springdemo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    //method- "Hello World"

    @GetMapping("/helloworld")
    public String helloWorld(){

        return "Hello World";
    }

    @GetMapping("/helloworldbean")
    public HelloWorldBean helloWorldBean(){

        return new  HelloWorldBean("Hello World");
    }

    @GetMapping("/helloworldbean/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){

        return new  HelloWorldBean(String.format("Hello World %s",name));
    }
}
