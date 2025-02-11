package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Configuration
@Component
@EnableScheduling
public class EmployeeDetailsAngularSpringAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailsAngularSpringAwsApplication.class, args);
	}

}
