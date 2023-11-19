package com.hotel.Exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ExerciseController {
    private final ExerciseRepository exerciseRepository;

    ExerciseController(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("/TestExercise")
    public String hello(){
        return "Hello Exercise World";
    }

    // @PostMapping("/AddExercise")
    // public void addExercise(@RequestParam String date, @RequestParam String type, @RequestParam int duration, @RequestParam String intensity )  {
    //     Exercise temp = new Exercise(date, type, duration, intensity);
    //     repo.save(temp);
    //     return;
    // }

}

