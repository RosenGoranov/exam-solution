package com.example.spotifyplaylistapp.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}