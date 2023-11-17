package com.hotel.Nutrition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.testUtil.RandomUtil;

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
    public List<String> food(){
        String SQL = "select NutrientID from `nutrient-amount` where FoodID = 2 limit 10";

        List<String> temp = jdbcTemplate.queryForList(SQL, String.class);

        List<String> res = new ArrayList<>();
        
        for(int i = 0; i < temp.size(); i++){
            String SQL1 = "select NutrientName from nutrientName where NutrientID =" + temp.get(i);
            List<String> curr = jdbcTemplate.queryForList(SQL1, String.class);
            //return this from db in more effective way later
            for(int j = 0; j < curr.size(); j++){
                res.add(curr.get(0));
            }
        }
       return res; 
    }
    @GetMapping("/SpecificFood")
     public String specificFood(@RequestParam String food){
        String SQL = "SELECT FoodID from cnf.Food WHERE FoodDescription LIKE '%"+food+"%' LIMIT 1";

        List<String> temp = jdbcTemplate.queryForList(SQL, String.class);

        return temp.get(0);
        

       //return "21"; 
    }

    @GetMapping("/CaloriesBurnt")
     public List<List<String>> caloriesBurnt(){
        List<List<String>> res = new ArrayList<>();
        List<String> days = new ArrayList<>();
        days.add("Monday");days.add("Tuesday");days.add("Wednesday");days.add("Thursday");days.add("Friday");days.add("Saturday");days.add("Sunday");
        List<String> caloriesBurned = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < 7; i++){
            String t = "" + rand.nextInt(230);
            caloriesBurned.add(t);
        }
        res.add(days);
        res.add(caloriesBurned);
        return res;
    }
}


