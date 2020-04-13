package com.saikiran.springdemo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "All Details about the User Model")
public class User extends RepresentationModel<User> {

    private  Integer id;

    @Size(min = 2, message = "Name Should be minimum 2 Characters Long")
    @ApiModelProperty(notes = "Name Should be minimum 2 Characters Long")
    private  String  name;

    @Past(message = "Date of birth should be past date")
    @ApiModelProperty(notes = "Date of birth should be past date")
    private  Date date;

    public User(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
