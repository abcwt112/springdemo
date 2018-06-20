package com.labofjet.springdemo.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class MyAutoConfiguration {

    //@AutoConfigureAfter(DispatcherServlet.class)
    @Configuration
    public static class MyConfig {
        public Object haha() {
            return new Object();
        }
    }
}
