package com.sw.s1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootMybtisThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybtisThymeleafApplication.class, args);
	}

}
