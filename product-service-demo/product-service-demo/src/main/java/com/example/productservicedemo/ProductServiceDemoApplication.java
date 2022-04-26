package com.example.productservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceDemoApplication.class, args);
	}

}
