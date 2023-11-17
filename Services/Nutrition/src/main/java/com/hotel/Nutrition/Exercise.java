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
    
}
