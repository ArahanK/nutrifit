package com.hotel.Exercise;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    //references exercise info database table

}
