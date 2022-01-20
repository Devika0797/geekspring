package com.geekbrains;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class GeekspringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeekspringApplication.class, args);
    }
}
