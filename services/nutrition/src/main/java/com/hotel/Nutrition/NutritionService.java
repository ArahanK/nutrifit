// package com.hotel.Nutrition;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;

//------------------------------------------------------------//
// what business logic even goes here?!?!?!? is this needed?? 
//-----------------------------------------------------------//



// @Service //business logic class
// public class NutritionService {

//     private final NutritionRepository nutritionRepository;
//     private static final Logger logger = LoggerFactory.getLogger(NutritionService.class);

//     @Autowired
//     public NutritionService(NutritionRepository nutritionRepository) {
//         this.nutritionRepository = nutritionRepository;
//     }

//     public List<Nutrition> getAllFoods() {
//         return nutritionRepository.findAll();
//     }

//     public Nutrition getNutritionByName(String name) {
//         logger.info("Searching for nutrition by name: {}", name);
//         Nutrition nutrition = nutritionRepository.findByName(name);
//         logger.info("Found nutrition: {}", nutrition);
//         return nutrition;
//     }

// }