package com.hotel.Nutrition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NutritionMapper implements RowMapper<Nutrition> {
    public Nutrition mapRow(ResultSet rs, int rowNum) throws SQLException {
        Nutrition nutrition = new Nutrition();
        nutrition.setFoodID(rs.getLong("foodID"));
        nutrition.setName(rs.getString("name"));
        nutrition.setCalories(rs.getInt("calories"));
        nutrition.setProtein(rs.getInt("protein"));
        nutrition.setCarbs(rs.getInt("carbs"));
        nutrition.setFat(rs.getInt("fat"));
        return nutrition;
    }
}