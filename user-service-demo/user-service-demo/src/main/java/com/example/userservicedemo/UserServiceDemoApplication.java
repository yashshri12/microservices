package com.example.userservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceDemoApplication {
	@Bean
	WebClient webClient(WebClient.Builder builder) {
		return builder.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserServiceDemoApplication.class, args);
	}

}
