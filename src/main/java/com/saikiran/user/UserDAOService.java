package com.saikiran.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDAOService {

    private static List<User> users= new ArrayList<>();

    private int userCount;

    static {

        users.add(new User(1,"John", new Date()));
        users.add(new User(2,"Simha", new Date()));
        users.add(new User(1,"Rajini", new Date()));

    }

    public List<User> finadAll(){

        return users;
    }

    public User findById(Integer id){

        for(User user: users){
            if(user.getId()==id){
                return  user;
            }
        }
        return  null;
    }

    public void add(User user){

        if(user.getId()!=null){
         user.setId(++userCount);
        }
        users.add(user);
    }
}
