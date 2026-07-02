package com.example.studentmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management System API")
                        .description("""
                                RESTful API for managing student records.
                                
                                **Features:**
                                - CRUD operations (Add, Update, Delete, Get)
                                - Search by Name, Department, Semester, CGPA, Email
                                - Full-text keyword search
                                - Pagination and Sorting
                                - Input validation
                                - Centralized exception handling
                                """)
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("SVC Engineering College - NEC")
                                .email("admin@svcnec.edu")
                                .url("https://www.svcnec.edu"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Local Development Server")
                ));
    }
}
