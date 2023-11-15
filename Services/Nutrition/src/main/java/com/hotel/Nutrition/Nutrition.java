package com.hotel.Nutrition;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 

@Entity
@Table(name="Foods")
public class Nutrition {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer FoodId;
    @Column
    private String name;
    @Column
    private int calories;
    @Column
    private int protein;
    @Column
    private int carbs;
    @Column
    private int fat;

    public Nutrition() {
        // Default constructor
    }

    //Getters
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
