package com.Labs.Patterns.dao;

import com.Labs.Patterns.dto.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public interface IUserDao {
    void addUser(User user) ;
    User findByNicknameAndPassword(String nickname,String password);
    User findByNickname(String nickname);
    int getIdByNickname(String nickname);
}
