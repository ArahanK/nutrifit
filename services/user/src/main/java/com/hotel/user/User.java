package com.hotel.user;

//import Services.ExerciseService;
// import Services.Nutrition;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


//-----------------User Class-----------------//
// this still needs to be connected to the database 
// and the foodlog class and exerciselog class??
//--------------------------------------------//

@Entity
@Table(name = "userInfo")
public class User {
    @Id
    @Column(name = "email") 
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;


    public User() {
        // Default constructor
    }   

    public User(String email, String password, String firstName, String lastName, int age, String sex, int weight, int height) {
        this.email = email;
        this.password = password;  
        this.firstName = firstName; 
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }


    // Setters
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAge(int age){
        this.age = age;
    }   
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setHeight(int height){
        this.height = height;
    }

    //Getters
    public String getEmail(){
        return this.email;
    }  
    public String getPassword(){
        return this.password;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public int getAge(){
        return this.age;
    }
    public String getSex(){
        return this.sex;
    }
    // public int getWeight(){
    //     return this.weight;
    // }
    // public int getHeight(){
    //     return this.height;
    // }   
    
}
