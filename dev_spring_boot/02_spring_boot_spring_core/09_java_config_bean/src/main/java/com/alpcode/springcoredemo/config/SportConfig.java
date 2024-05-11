package com.alpcode.springcoredemo.config;

import com.alpcode.springcoredemo.common.Coach;
import com.alpcode.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
