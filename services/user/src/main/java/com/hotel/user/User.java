package com.hotel.user;

//import Services.ExerciseService;
// import Services.Nutrition;
import jakarta.persistence.*;


//-----------------User Class-----------------//
// this still needs to be connected to the database 
//  exerciselog class
//--------------------------------------------//

@Entity
@Table(name = "userInfo")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "userID")
    private long userId;

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
    private int weight;

    @Column(name = "height")
    private int height;


    public User() {
        // Default constructor
    }   

    public User(long userID, String email, String password, String firstName, String lastName, int age, String sex, int weight, int height) {
        this.userId = userID;
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
    public void setUserId(long userId){
        this.userId = userId;
    }
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
    public long getUserId(){
        return this.userId;
    }
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
    public int getWeight(){
        return this.weight;
    }
    public int getHeight(){
        return this.height;
    }   

    public void addEntry(String date, String foodName, int servings) {
        //???
    }
    
}
