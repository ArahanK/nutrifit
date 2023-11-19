package com.hotel.user;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UserController {
    
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    UserController(UserRepository userRepository, JdbcTemplate jdbcTemplate){
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello, this is a test for User!";
    }

    @GetMapping("/getAllUsers") //get all users in db
    public List<User> user() {
        String query = """
                SELECT *
                FROM `userInfo`
            """;
        return jdbcTemplate.query(query, new UserMapper());
    }

    @PostMapping
    public void addUser(User user) {
        userRepository.save(user);
    }

    @GetMapping("/user/{email}") //get user by email and return its info
    public List<User> userByEmail(@PathVariable String email) {
        String query = """
            SELECT *
            FROM `userInfo`
            WHERE email LIKE '%%%s%%'
        """;
        String SQL = String.format(query, email);

        List<User> temp = jdbcTemplate.query(SQL, new UserMapper());
        return temp;
    }







    // user (email), dateAdded (date), foodName, quantity
    // user (email), dateAdded (date), foodName, quantity
    // user (email), dateAdded (date), foodName, quantity
    // user (email), dateAdded (date), foodName, quantity
    // user (email), dateAdded (date), foodName, quantity
    // user (email), dateAdded (date), foodName, quantity
    // user (email), dateAdded (date), foodName, quantity
    // user (email), dateAdded (date), foodName, quantity


    
    

    

   //-----foodlog setters and getters-----//

   // Change this to a POST request
   // Change the @PathVariable mapping to a @RequestBody mapping
   @GetMapping("/user/test/{food}")
   public void addEntry(@PathVariable String food) {
    String apiUrl = "http://localhost:8080/food/" + food;
        // Create an instance of HttpClient
        // HttpClient httpClient = HttpClient.newHttpClient();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make the GET request and receive the response
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // Print the response status code and body
        System.out.println("Response Code: " + responseEntity.getStatusCode());
        System.out.println("Response Body: " + responseEntity.getBody());

    // Make API call to get nutriton data from nutrition service
    // Throw error if the food does not exist or cannot be found
    // multiply nutrition data by servings
    // write food log entry to database


    //    foodLog.addEntry(date, food, servings);
   }





    // public FoodLog getFoodLog() {
    //     return foodLog;
    // }

    // @Override
    // public String toString() {
    //     return "User{" +
    //             "foodLog=" + foodLog +
    //             '}';
    //}


    //exerciselog setters and getters
}
