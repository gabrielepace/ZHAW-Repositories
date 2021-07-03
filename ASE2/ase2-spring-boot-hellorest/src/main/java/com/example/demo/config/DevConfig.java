package com.example.demo.config;

import com.example.demo.bootstrap.DatabaseBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines a Bean for the DatabaseBootstrap
 */
@Configuration
public class DevConfig {

    @Bean
    public DatabaseBootstrap databaseBootstrap() {
        return new DatabaseBootstrap();
    }

}