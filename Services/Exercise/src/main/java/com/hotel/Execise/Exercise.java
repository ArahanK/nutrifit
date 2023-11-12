package com.hotel.Nutrition;

import java.sql.Date;

import javax.xml.crypto.Data;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Exercise {
    private @Id @GeneratedValue Long id;
    private String date;
    private String type;
    private int duration;
    private String intensisty;

    Exercise(String date, String type, int duration, String intensity){
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.intensisty = intensity;
    }

    //Getters and setters
    public String getDate(){
        return this.date;
    }   
    public String getType(){
        return this.type;
    }
    public int getDuration(){
        return this.duration;
    }
    public String getIntensity(){
        return this.intensisty;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public void setIntensity(String intensity){
        this.intensisty = intensity;
    }
    
    
}
