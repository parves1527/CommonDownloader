package com.eight41.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value= {"classpath:config.properties"})
public class Config
{    
    @Value("${storage.path}")
    private String storagePath;

    public String getStoragePath()
    {
        return storagePath;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() 
    {
        return new PropertySourcesPlaceholderConfigurer();
    }    
}
