package com.hotel.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setAge(rs.getInt("age"));
        user.setSex(rs.getString("sex"));
        user.setWeight(rs.getInt("weight"));
        user.setHeight(rs.getInt("height"));
        return user;
    }
}
