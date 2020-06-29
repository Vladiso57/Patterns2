package com.Labs.Patterns.mapper;

import com.Labs.Patterns.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<User> {


    public User mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        String nickname=rs.getString("nickname");
        String password=(rs.getString("password"));
        int idUser=(rs.getInt("id_user"));
        return new User(nickname,password,idUser);
    }
}
