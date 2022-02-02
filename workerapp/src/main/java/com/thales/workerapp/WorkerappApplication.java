package com.thales.workerapp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class WorkerappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkerappApplication.class, args);
	}

}
