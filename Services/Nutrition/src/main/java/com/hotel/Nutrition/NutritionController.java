
package com.hotel.Nutrition;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class NutritionController {

    private final NutritionService nutritionService;

    @Autowired
    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello, this is a test!";
    }

    @GetMapping("/AllFoods")
    public List<Nutrition> getAllFoods() {
        return nutritionService.getAllFoods();
    }

    // Get nutrition info by name
    @GetMapping("/nutrition/{name}")
    public Nutrition getNutritionByName(@PathVariable String name) {
        return nutritionService.getNutritionByName(name);
    }
}
