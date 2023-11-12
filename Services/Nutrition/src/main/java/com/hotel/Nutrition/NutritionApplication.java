package com.hotel.Nutrition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// class DatabaseConnector {
//     private static final String JDBC_URL = "jdbc:your_database_url";
//     private static final String USERNAME = "your_username";
//     private static final String PASSWORD = "your_password";

//     static Connection connect() throws SQLException {
//         return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//     }
// }


@SpringBootApplication
public class NutritionApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionApplication.class, args);

		String query = "SELECT f.food_name, f.calories, m.protein, m.carbohydrates, m.fat " +
		"FROM foods f " +
		"JOIN macro_info m ON f.food_id = m.food_id " +
		"WHERE f.food_name = ?";
	}
}
