package com.saikiran.springdemo.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //method- "Hello World"

    @GetMapping("/helloworld")
    public String helloWorld(){

        return "Hello World";
    }

    @GetMapping("/helloworld-internationalize")
    public String helloWorld(@RequestHeader(name = "Accept-Language", required =false) Locale locale){

        return messageSource.getMessage("good.morning.message",null,locale);

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
