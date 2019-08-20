package com.verizon.springmicroservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringMicroserviceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroserviceDemoApplication.class, args);
	}

}
