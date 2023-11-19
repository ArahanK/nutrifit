package com.hotel.user;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);

		// User userTest = new User("testEmail@gmail.com", "password", "Bob", "McBobby", 54, "male", 200, 90);
		// userTest.addEntry.("2023-11-16", "Apple", 2);

        // List<FoodLog.FoodEntry> entriesForUser1 = user1.getFoodLog().getEntries();

		// System.out.println("User 1: " + entriesForUser1);	
	}


}
