package com.hotel.Nutrition;

import java.sql.Date;

import javax.xml.crypto.Data;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Nutrition {
    private string foodName;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;

    // Contructor
    Nutrition(String foodName, int calories, int protein, int carbs, int fat){
        this.foodName = foodName;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    //Getters
    public String getFoodName(){
        return this.foodName;
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
