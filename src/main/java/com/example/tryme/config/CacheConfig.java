package com.example.tryme.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public ConcurrentHashMap<String, Object> customCache() {
        return new ConcurrentHashMap<>();
    }
}