package com.hotel.user;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// import java.util.List;
// import java.util.ArrayList;

// // @RestController
// public class FoodLog {

//     private static List<FoodLog> entries;

//     public FoodLog(String date, String foodName, int quantity) {
//         FoodLog.entries = new ArrayList<FoodLog>();
//     }

//     public void addEntry(FoodLog entry) {
//         entries.add(entry);
//     }

//     @GetMapping("/foodlog")
//     public static List<FoodLog> getEntries() {
//         return entries;
//     }
    
// }

import java.util.ArrayList;
import java.util.List;

public class FoodLog {
    private List<FoodEntry> entries;

    public FoodLog() {
        this.entries = new ArrayList<>();
    }

    // public void addEntry(String date, String foodName, int quantity) {
    //     FoodEntry entry = new FoodEntry(date, foodName, quantity);
    //     entries.add(entry);
    // }

    public List<FoodEntry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "FoodLog{" +
                "entries=" + entries +
                '}';
    }

    public static class FoodEntry {
        private String date;
        private String foodName;
        private int quantity;

        public FoodEntry(String date, String foodName, int quantity) {
            this.date = date;
            this.foodName = foodName;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "FoodEntry{" +
                    "date='" + date + '\'' +
                    ", foodName='" + foodName + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    

}



