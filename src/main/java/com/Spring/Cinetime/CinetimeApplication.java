package com.Spring.Cinetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CinetimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinetimeApplication.class, args);
	}

}
