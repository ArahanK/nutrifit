package com.hotel.user;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private String nutritionServiceBaseURL = "http://localhost:8080/";
    private String userServiceBaseURL = "http://localhost:8081/";

    UserController(UserRepository userRepository, JdbcTemplate jdbcTemplate){
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello, this is a test for User!";
    }

    //--------------------------------------------//
    //----------------user methods----------------//
    //--------------------------------------------//
    @GetMapping("/getAllUsers") //get all users in db
    public List<User> user() {
        String query = """
                SELECT *
                FROM `userInfo`
            """;
        return jdbcTemplate.query(query, new UserMapper());
    }
    //Method being used my frontend which arahan made
    @PostMapping("/user/AddNewUser")
    public ResponseEntity<Object> addUser(@RequestParam String email, @RequestParam String password, @RequestParam String First_name, @RequestParam String last_name, @RequestParam int Age, 
    @RequestParam String Sex, @RequestParam int weight, @RequestParam int height){

       String specialCharacter = ".*[^a-z0-9 ].*";
       String number = ".*[0-9].*";
       String uppercase = ".*[A-Z].*";
        if(password.length() < 8 || !password.matches(specialCharacter)
           || !password.matches(number) || !password.matches(uppercase)){
            //can return more info abt whats spefically wrong when refactor
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //Validate email does not exist 100% a better way to do this but just using this for now
        String emailQuery = "SELECT email FROM users.userInfo";
        List<String> temp = jdbcTemplate.queryForList(emailQuery, String.class);

        for(int i = 0; i < temp.size(); i++){
            if(email.equals(temp.get(i))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

      jdbcTemplate.update(
    "INSERT INTO users.userInfo (email, password, firstName, lastName, age, sex, weight, height) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
    email, password, First_name, last_name, Age, Sex, weight, height
    );
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/addUser") //add user to db (need to add error handling   
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        String apiUrl = userServiceBaseURL + "user/" + user.getUserId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        
        //Validate some basic password criteria
        String password = user.getPassword();
        String specialCharacter = ".*[^a-z0-9 ].*";
        String number = ".*[0-9].*";
        String uppercase = ".*[A-Z].*";
        if(password.length() < 8 || !password.matches(specialCharacter)
           || !password.matches(number) || !password.matches(uppercase)){
            //can return more info abt whats spefically wrong when refactor
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String query = """
            INSERT INTO `userInfo` (email, password, firstName, lastName, age, sex, weight, height)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(query, user.getEmail(), user.getPassword(), user.getFirstName(),user.getLastName(), user.getAge(), user.getSex(), user.getWeight(), user.getHeight());

         return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //get user by email and return its info
    @GetMapping("/user/email/{email}") 
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

    //get user by id
     //todo: edit to hide password
    @GetMapping("/user/id/{id}") 
    public List<User> userById(@PathVariable String id) {
        String query = """
            SELECT *
            FROM `userInfo`
            WHERE userId LIKE '%%%s%%'
        """;
        String SQL = String.format(query, id);

        List<User> temp = jdbcTemplate.query(SQL, new UserMapper());
        return temp;
    }

    //delete user by email
    @DeleteMapping("/user/deleteUser/{email}")
    public ResponseEntity<Object> deleteUser(@RequestBody User user) {
        String query = """
            DELETE FROM `userInfo`
            WHERE email = ?
        """;
        jdbcTemplate.update(query, user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //todo: update user by email and password verification 
    
    //-----------------------------------------------//
    //----------------foodlog methods----------------//
    //-----------------------------------------------//
   @PostMapping("/user/AddEntry") //only works if user exists
   public ResponseEntity<Object> addEntry(@RequestBody FoodLog foodLog) {
        String apiUrl = nutritionServiceBaseURL + "food/" + foodLog.getFoodName();

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make the GET request and receive the response
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if(responseEntity.getBody() == null || responseEntity.getBody().isEmpty() || responseEntity.getBody().equals("[]")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Food not found");
        }
        
        try {
            int[] nutritionInfo = parseNutritionInfo(responseEntity.getBody());
            int calories = nutritionInfo[0] * foodLog.getServings();
            int protein = nutritionInfo[1] * foodLog.getServings();
            int carbs = nutritionInfo[2] * foodLog.getServings();
            int fat = nutritionInfo[3] * foodLog.getServings();
            Instant date = Instant.now();

            String query = """
                INSERT INTO `foodLogs` (email, foodName, dateAdded, servings, calories, protein, carbs, fat)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

            jdbcTemplate.update(query, foodLog.getEmail(), foodLog.getFoodName(), date, foodLog.getServings(), calories, protein, carbs, fat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing JSON: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public static int[] parseNutritionInfo(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        if (jsonNode.isArray() && jsonNode.size() > 0) {
            JsonNode firstItem = jsonNode.get(0);

            int calories = firstItem.get("calories").asInt();
            int protein = firstItem.get("protein").asInt();
            int carbs = firstItem.get("carbs").asInt();
            int fat = firstItem.get("fat").asInt();

            return new int[]{calories, protein, carbs, fat};
        } else {
            throw new IllegalArgumentException("Invalid JSON format");
        }
    }

    //delete food log entry

    //update food log entry

    //get food log entries by email

    // get food log entries by date 
    
    // get food log entries by date range

    // get food log entries by food name


    //---------------------------------------------------//
    //----------------exerciselog methods----------------//
    //---------------------------------------------------//


    
    //add exercise log entry

    //delete exercise log entry

    //update exercise log entry

    //get exercise log entries by email

    // get exercise log entries by date

    // get exercise log entries by date range

    // get exercise log entries by exercise name

    



}
