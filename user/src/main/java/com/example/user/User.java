package com.example.user;

//import Services.ExerciseService;
// import Services.Nutrition;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


//-----------------User Class-----------------//
// this still needs to be connected to the database 
// and the foodlog class and exerciselog class!!!!!
//--------------------------------------------//

// @Entity
// @Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userID;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "gender") //true=female false=male
    private boolean gender;

    // public void addFoodLogEntry(String date, String foodName, int quantity) {
    //     FoodLog entry = new FoodLog(date, foodName, quantity);
    //     entry.addEntry(entry);
    // }

    // //get foodlog entries
    // public List<FoodLog> getFoodLogEntries() {
    //     return FoodLog.getEntries();
    // }

    // //getters and setters
    // public Long getUserID() {
    //     return userID;
    // }
    
    // public void setUserID(Long userID) {
    //     this.userID = userID;
    // }

    // public String getFirstName() {
    //     return firstName;
    // }
 
    private FoodLog foodLog;

    public User(Long userID, String firstName, String lastName, int age, double weight, double height, boolean gender){
        this.foodLog = new FoodLog();
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }
    

    public FoodLog getFoodLog() {
        return foodLog;
    }

    @Override
    public String toString() {
        return "User{" +
                "foodLog=" + foodLog +
                '}';
    }
}
