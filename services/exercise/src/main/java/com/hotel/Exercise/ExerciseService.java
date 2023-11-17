package com.hotel.Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExerciseService {

    public static double calculateCaloriesBurned(String exercise, double duration, double intensity) {
        // MET values for different exercises (you can extend this list)
        Map<String, Double> metValues = new HashMap<>();
        metValues.put("running", 7.0);
        metValues.put("cycling", 4.0);
        metValues.put("swimming", 8.0);
        metValues.put("walking", 3.5);
        // add more exercises and their MET values as needed

        // Validate input
        if (!metValues.containsKey(exercise)) {
            System.out.println("Invalid exercise");
            return -1;
        }

        if (duration <= 0) {
            System.out.println("Duration should be a positive number");
            return -1;
        }

        if (intensity <= 0) {
            System.out.println("Intensity should be a positive number");
            return -1;
        }

        // Calculate calories burned using the formula: Calories = MET * weight in kg * duration in hours
        double weightKg = 70;  // You can replace this with the user's actual weight
        double met = metValues.get(exercise);
        double caloriesBurned = met * weightKg * duration * intensity;

        return caloriesBurned;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the exercise (e.g., running, cycling, swimming, walking): ");
        String exerciseInput = scanner.nextLine();

        System.out.print("Enter the duration in hours: ");
        double durationInput = scanner.nextDouble();

        System.out.print("Enter the intensity factor (e.g., 1.0 for normal intensity): ");
        double intensityInput = scanner.nextDouble();

        double result = calculateCaloriesBurned(exerciseInput, durationInput, intensityInput);

        if (result != -1) {
            System.out.println("Calories burned: " + result + " kcal");
        }

        scanner.close();
    }

}