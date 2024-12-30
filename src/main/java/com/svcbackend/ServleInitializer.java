package com.svcbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServleInitializer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SvcBackendApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SvcBackendApplication.class);
    }

}
