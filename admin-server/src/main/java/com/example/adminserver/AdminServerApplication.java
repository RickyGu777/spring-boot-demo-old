package com.example.adminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AdminServerApplication.class, args);
        new SpringApplicationBuilder(AdminServerApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }

}