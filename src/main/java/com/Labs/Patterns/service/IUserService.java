package com.Labs.Patterns.service;

import com.Labs.Patterns.dto.User;

public interface IUserService {
    void registerUser(User user);
    boolean isUserExist(String nickname);
    int getUsersId(String nickname);
}
