package com.hotel.Nutrition;

import org.springframework.data.jpa.repository.JpaRepository;

interface NutritionRepository extends JpaRepository<Nutrition, Long> {

     Nutrition findByName(String name);
//    List<Nutrition> findAll();
}
