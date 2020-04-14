package com.saikiran.springdemo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    //URI Versioning
    @GetMapping("v1/person")
     public PersonV1 getPersonV1(){
        return new PersonV1("Sai Kiran") ;
     }

    //URI Versioning
    @GetMapping("v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("sai","kiran")) ;
    }

    //Request Param Versioning
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 getPersonParamV1(){
        return new PersonV1("Sai Kiran") ;
    }

    //Request Param Versioning
    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 getPersonParamV2(){
        return new PersonV2(new Name("sai","Kiran")) ;
    }


    //Request Header Versioning
    @GetMapping(value = "/person/header", headers = "X-API_VERSION=1")
    public PersonV1 getPersonHeaderV1(){
        return new PersonV1("Sai Kiran") ;
    }

    //Request Header Versioning
    @GetMapping(value = "/person/header", headers = "X-API_VERSION=2")
    public PersonV2 getPersonHeaderV2(){
        return new PersonV2(new Name("sai","Kiran")) ;
    }

    //MIME Versioning or Accept Version or Content Negotiation Versioning
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonProducesV1(){
        return new PersonV1("Sai Kiran") ;
    }

    //Request Header Versioning
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonProducesV2(){
        return new PersonV2(new Name("sai","Kiran")) ;
    }

}
