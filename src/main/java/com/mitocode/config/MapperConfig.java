package com.mitocode.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean("courseMapper")
    public ModelMapper courseMapper(){
        return new ModelMapper();
    }
    @Bean("studentMapper")
    public ModelMapper studentMapper(){
        return new ModelMapper();
    }
    @Bean("enrollMapper")
    public ModelMapper enrollMapper(){
        return new ModelMapper();
    }
}
