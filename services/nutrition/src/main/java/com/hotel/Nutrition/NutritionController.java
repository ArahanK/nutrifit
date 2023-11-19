
package com.hotel.Nutrition;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

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
        String query = """
                SELECT *
                FROM `sampleFoods`
            """;
        return jdbcTemplate.query(query, new NutritionMapper());
    }

    // @GetMapping("/food/{name}") //get food by name and return its nutrition info
    // public Nutrition foodByName(@PathVariable String name) {
    //     String query = """
    //         SELECT *
    //         FROM `sampleFoods`
    //         WHERE name = %s
    //     """;
    //     String SQL = String.format(query, name);

    //     List<Nutrition> temp = jdbcTemplate.query(SQL, new NutritionMapper());
    //     return temp.get(0);
    // }

    @GetMapping("/food/{name}") //get food by name and return its nutrition info
    public List<Nutrition> foodByName(@PathVariable String name) {
        String query = """
            SELECT *
            FROM `sampleFoods`
            WHERE name LIKE '%%%s%%'
        """;
        String SQL = String.format(query, name);

        return jdbcTemplate.query(SQL, new NutritionMapper());
    }
}
    


