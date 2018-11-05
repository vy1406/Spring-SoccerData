package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.model.dummyObject;

@SpringBootApplication
public class OnlineSoccerApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(OnlineSoccerApplication.class, args);
	}
}
