package com.hotel.Exercise;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Date;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
class ExerciseController {
<<<<<<< HEAD
    private final ExerciseRepository exerciseRepository;

    ExerciseController(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
=======
    private final JdbcTemplate jdbcTemplate;
   //need to get this value from user service
   int userWeight = 65;

    ExerciseController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
>>>>>>> 5afae690bc3fc1e8bca3ae7717a30647d83733a0
    }


    @PostMapping("/LogDiet")
    public void addDiet(@RequestParam String email) {}
    

    @PostMapping("/LogExercise")
    public void addExercise( @RequestParam String email, @RequestParam String type, @RequestParam int length,@RequestParam("date") Date date, @RequestParam String intensity){
      int caloriesBurnt = 0;
      //Calculations for running
      if(type.equals("running") && intensity.equals("low")){
        caloriesBurnt = ((userWeight * 7)/200) * length;
      }else if(type.equals("running") && intensity.equals("medium")){
        caloriesBurnt = ((userWeight * 9)/200) * length;
      }else if(type.equals("running") && intensity.equals("high")){
        caloriesBurnt = ((userWeight * 12)/200) * length;
      }

      //Calculations for biking
      if(type.equals("biking") && intensity.equals("low")){
        caloriesBurnt = ((userWeight * 4)/200) * length;
      }else if(type.equals("biking") && intensity.equals("medium")){
        caloriesBurnt = ((userWeight * 6)/200) * length;
      }else if(type.equals("biking") && intensity.equals("high")){
        caloriesBurnt = ((userWeight * 8)/200) * length;
      }

      //Calculations for swiming
      if(type.equals("swimming") && intensity.equals("low")){
        caloriesBurnt = ((userWeight * 4)/200) * length;
      }else if(type.equals("swimming") && intensity.equals("medium")){
        caloriesBurnt = ((userWeight * 7)/200) * length;
      }else if(type.equals("swimming") && intensity.equals("high")){
        caloriesBurnt = ((userWeight * 10)/200) * length;
      }

      //Calculations for others, assuming others is assorted weightlifting
      if(type.equals("others") && intensity.equals("low")){
        caloriesBurnt = ((userWeight * 3)/200) * length;
      }else if(type.equals("others") && intensity.equals("medium")){
        caloriesBurnt = ((userWeight * 4)/200) * length;
      }else if(type.equals("others") && intensity.equals("high")){
        caloriesBurnt = ((userWeight * 5)/200) * length;
      }

      String SQL = "INSERT INTO exercise.exercise_log (email, type, length, date, intensity, caloriesBurnt) VALUES ("+"'"+email+"'"+","+"'"+ type +"'"+" ,"+"'"+length+"'"+","+"'"+date+"','"+intensity+"','"+caloriesBurnt+"')";
      jdbcTemplate.update(SQL);
    }

    @GetMapping("/GetExercise")
    public List<List<String>> getExercise( @RequestParam String email){
      List<List<String>> res = new ArrayList<>();

      // for loop later
      String SQL1 = "SELECT type FROM exercise.exercise_log WHERE email = "+"'"+email+"'"+" ORDER BY date DESC LIMIT 1";
      List<String> temp1 = jdbcTemplate.queryForList(SQL1, String.class);
      String SQL2 = "SELECT length FROM exercise.exercise_log WHERE email = "+"'"+email+"'"+" ORDER BY date DESC LIMIT 1";
      List<String> temp2 = jdbcTemplate.queryForList(SQL2, String.class);
      String SQL3 = "SELECT date FROM exercise.exercise_log WHERE email = "+"'"+email+"'"+" ORDER BY date DESC LIMIT 1";
      List<String> temp3 = jdbcTemplate.queryForList(SQL3, String.class);
      String SQL4 = "SELECT intensity FROM exercise.exercise_log WHERE email = "+"'"+email+"'"+" ORDER BY date DESC LIMIT 1";
      List<String> temp4 = jdbcTemplate.queryForList(SQL4, String.class);

      res.add(temp1);res.add(temp2);res.add(temp3);res.add(temp4);
      return res;
    }

    @GetMapping("/CaloriesBurntOn")
    public int getAvrgCalories( @RequestParam String email, @RequestParam Date day){
      String SQL = "SELECT caloriesBurnt FROM exercise.exercise_log WHERE email = "+"'"+email+"' and date ="+"'"+day+"'"+"";
      List<Integer> temp = jdbcTemplate.queryForList(SQL, Integer.class);
      return temp.get(0);
    }

    @GetMapping("/AverageCaloriesBurnt")
    public int getAvrgCalories( @RequestParam String email, @RequestParam int days){
      //On frontend we can calculate the days between the two dates
      String SQL1 = "SELECT caloriesBurnt FROM exercise.exercise_log WHERE email = "+"'"+email+"'"+" ORDER BY date DESC LIMIT "+days;
      List<Integer> temp = jdbcTemplate.queryForList(SQL1, Integer.class);
      int total = 0;
      for(int i = 0; i < temp.size(); i++){
        total+=temp.get(i);
      }
      return total/days;
    }

    @GetMapping("/predict-fat-loss")
    public int returnFatLossWeight(@RequestParam String email, @RequestParam("date") Date date, @RequestParam int weight) {
      
      return 1000;
    }

    @GetMapping("/users")
    public void addUser(@RequestParam String email, @RequestParam String password, @RequestParam String First_name, @RequestParam String last_name, @RequestParam int Age, 
    @RequestParam String Sex, @RequestParam int weight, @RequestParam int height){
      String SQL = "INSERT INTO users.userInfo (Email, Password, firstName, lastName, Age, Sex, Weight, height) VALUES ('" + email + "', '" + password + "', '" + First_name + "', '" + last_name + "', " + Age + ", '" + Sex + "', " + weight + ", " + height + ")";
      jdbcTemplate.update(SQL);
    }

    @GetMapping("/visualize-top-5")
    public void visualizeTop5Nutrients(@RequestParam String email, @RequestParam("date") Date startDate, @RequestParam("date") Date endDate) {
      String SQL = "SELECT SUM(protein) AS total_protein, SUM(carbs) AS total_carbs, SUM(fat) AS total_fat, SUM(kcal) AS total_kcal FROM test_diet.test_diet_table WHERE email = '" + email + "' AND date BETWEEN '" + startDate + "' AND '" + endDate + "'";
      jdbcTemplate.queryForList(SQL);
    }
  }