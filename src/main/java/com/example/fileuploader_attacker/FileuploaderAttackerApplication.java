package com.example.fileuploader_attacker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FileuploaderAttackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileuploaderAttackerApplication.class, args);
	}

}
