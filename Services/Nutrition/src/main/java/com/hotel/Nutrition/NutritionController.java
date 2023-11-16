
package com.hotel.Nutrition;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class NutritionController {

    // private final NutritionService nutritionService;
    private final JdbcTemplate jdbcTemplate;
    private final NutritionRepository nutritionRepository;

    // @Autowired
    // public NutritionController(NutritionService nutritionService) {
    //     this.nutritionService = nutritionService;
    // }

    NutritionController(NutritionRepository nutritionRepository, JdbcTemplate jdbcTemplate){
        this.nutritionRepository = nutritionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    
    @GetMapping("/test")
    public String test() {
        return "Hello, this is a test!";
    }

    @GetMapping("/getFoods") //get all foods in db 
    public List<Nutrition> food() {
        String SQL = "select * from `Foods`";
        // System.out.println("SQL Query: " + SQL);

        List<Nutrition> temp = jdbcTemplate.query(SQL, new NutritionMapper());
        // System.out.println("Number of results from the query: %d" + temp.size());
        for (int i=0; i<temp.size(); i++) {
            System.out.println("Results from the database: " + temp.get(i).getName());
        }
        // System.out.println("Results from the database: " + temp.toString());
        return temp; 
    }

    @GetMapping("/food/{name}") //get food by name and return its nutrition info
    public Nutrition foodByName(@PathVariable String name) {
        String SQL = "select * from `Foods` where name = '" + name + "'";
        // System.out.println("SQL Query: " + SQL);

        List<Nutrition> temp = jdbcTemplate.query(SQL, new NutritionMapper());
        // System.out.println("Number of results from the query: %d" + temp.size());
        for (int i=0; i<temp.size(); i++) {
            System.out.println("Results from the database: " + temp.get(i).getName());
        }
        // System.out.println("Results from the database: " + temp.toString());
        return temp.get(0); 
    }
}
    



