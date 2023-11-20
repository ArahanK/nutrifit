package com.hotel.Nutrition;

import org.springframework.data.jpa.repository.JpaRepository;

interface NutritionRepository extends JpaRepository<Nutrition, Long> {

     Nutrition findByName(String name);
     // Nutrition addFood(String name, int calories, int protein, int carbs, int fat);
}
