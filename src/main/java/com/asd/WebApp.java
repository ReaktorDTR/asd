package com.asd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@PropertySource("classpath:application.properties")
@SpringBootApplication
@Configuration
@ControllerAdvice
@EnableWebMvc
@EnableAutoConfiguration
public class WebApp {

	public static void main(String... args) {
		SpringApplication.run(WebApp.class, args);
	}

}


