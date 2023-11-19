package com.hotel.Nutrition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 

@Entity
@Table(name = "sampleFoods")
public class Nutrition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodID")
    private Long foodID;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private int calories;
    
    @Column(name = "protein")
    private int protein;

    @Column(name = "carbs")
    private int carbs;

    @Column(name = "fat")
    private int fat;

    public Nutrition() {
        // Default constructor
    }

    public Nutrition(Long foodID, String name, int calories, int protein, int carbs, int fat) {
        this.foodID = foodID;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    // Setters
    public void setFoodID(Long foodID){
        this.foodID = foodID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCalories(int calories){
        this.calories = calories;
    }
    public void setProtein(int protein){
        this.protein = protein;
    }
    public void setCarbs(int carbs){
        this.carbs = carbs;
    }
    public void setFat(int fat){
        this.fat = fat;
    }

    // Getters
    public String getName(){
        return this.name;
    }
    public int getCalories(){
        return this.calories;
    }
    public int getProtein(){
        return this.protein;
    }
    public int getCarbs(){
        return this.carbs;
    }
    public int getFat(){
        return this.fat;
    }
}