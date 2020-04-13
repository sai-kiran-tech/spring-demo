package com.saikiran.springdemo.filters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    //Dynamic Filtering
    @GetMapping("/filtering")
    public MappingJacksonValue getSomeBean(){
       SomeBean someBean= new SomeBean("value1","value2","value3");

       PropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("value1");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping= new MappingJacksonValue(someBean);

        mapping.setFilters(filters);

        return mapping;
    }


}
