package com.pratian.SettingsService;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Settings Service", version = "1.0", description = "API for Settings Service"))
//@EnableEurekaClient
@SpringBootApplication
public class SettingsServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(SettingsServiceApplication.class, args);

	}

}
