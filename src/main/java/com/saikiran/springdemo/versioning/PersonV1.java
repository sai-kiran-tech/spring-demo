package com.saikiran.springdemo.versioning;

public class PersonV1 {

    String fullname;

    public PersonV1(){

    }

    public PersonV1(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "fullname='" + fullname + '\'' +
                '}';
    }
}
