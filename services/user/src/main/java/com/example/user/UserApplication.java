package com.example.user;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
		
		User user1 = new User((long) 12334567, "Steve", "Jobs", 78, 210,  99, false);
        user1.getFoodLog().addEntry("2023-11-16", "Apple", 2);

        User user2 = new User((long) 88337289, "Judy", "Whatever", 88, 199,  28, true);
        user2.getFoodLog().addEntry("2023-11-16", "Banana", 1);

        List<FoodLog.FoodEntry> entriesForUser1 = user1.getFoodLog().getEntries();
        List<FoodLog.FoodEntry> entriesForUser2 = user2.getFoodLog().getEntries();

		System.out.println("User 1: " + entriesForUser1);	
		System.out.println("User 2: " + entriesForUser2);
	}


}
