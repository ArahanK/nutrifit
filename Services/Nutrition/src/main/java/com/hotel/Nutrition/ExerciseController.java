package com.hotel.Nutrition;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
class ExerciseController {
    private final ExerciseRepository repo;
    private final JdbcTemplate jdbcTemplate;

    ExerciseController(ExerciseRepository repo, JdbcTemplate jdbcTemplate){
        this.repo = repo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/Hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/AddExercise")
    public void addExercise(@RequestParam String date, @RequestParam String type, @RequestParam int duration, @RequestParam String intensity )  {
        Exercise temp = new Exercise(date, type, duration, intensity);
        repo.save(temp);
        return;
    }

    @GetMapping("/Food")
    public List<List<String>> food(){
        String SQL = "select NutrientID from `nutrient-amount` where FoodID = 2";

        List<String> temp = jdbcTemplate.queryForList(SQL, String.class);

        List<List<String>> res = new ArrayList<>();
        
        for(int i = 0; i < temp.size(); i++){
            String SQL1 = "select NutrientName from nutrientName where NutrientID =" + temp.get(i);
            res.add(jdbcTemplate.queryForList(SQL1, String.class));
        }
       return res; 
    }
}


