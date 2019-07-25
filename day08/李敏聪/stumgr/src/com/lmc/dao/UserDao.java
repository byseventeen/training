package com.lmc.dao;

import com.lmc.model.User;
import com.lmc.util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public boolean addOne(User u) throws SQLException {
        String sql = "INSERT INTO student(stu_name,gender,birthdate,phone,hobby) VALUES (?,?,?,?,?)";
        int gender = 0;
        if ("男".equals(u.getGender())){
            gender = 1;
        }
        PreparedStatement stmt = MysqlConnection.getConnection().prepareStatement(sql);
        stmt.setString(1,u.getUsername());
        stmt.setInt(2,gender);
        stmt.setDate(3, new java.sql.Date(u.getBirthdate().getTime()));
        stmt.setString(4,u.getPhone());
        stmt.setString(5,u.getHobby());
        boolean result = stmt.execute();
        MysqlConnection.getConnection().close();
        return result;
    }

    public List<User> list() throws SQLException {
        String sql = "SELECT * FROM student";
        PreparedStatement stmt = MysqlConnection.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()){
            User u = new User();
            u.setId(rs.getInt("stu_id"));
            u.setUsername(rs.getString("stu_name"));
            if (rs.getInt("gender") == 1)
                u.setGender("男");
            else
                u.setGender("女");
            u.setPhone(rs.getString("phone"));
            u.setHobby(rs.getString("hobby"));
            u.setBirthdate(rs.getDate("birthdate"));
            users.add(u);
        }
        return users;
    }

}
