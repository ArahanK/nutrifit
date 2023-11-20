package com.hotel.user;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);

		// User userTest = new User("testEmail@gmail.com", "password", "Bob", "McBobby", 54, "male", 200, 90);
		// userTest.addEntry("2021-04-01", "apple", 1);

        // List<FoodLog.FoodEntry> entriesForUser1 = userTest.getFoodLog().getEntries();

		// System.out.println("User 1: " + entriesForUser1);	
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}
}
