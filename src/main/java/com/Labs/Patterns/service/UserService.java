package com.Labs.Patterns.service;

import com.Labs.Patterns.dao.IUserDao;
import com.Labs.Patterns.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao iUserDao;


    @Override
    public void registerUser(User user) {
        this.iUserDao.addUser(user);
    }

    @Override
    public boolean isUserExist(String nickname) {
        User u=iUserDao.findByNickname(nickname);
        if(u!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int getUsersId(String nickname) {
        return this.iUserDao.getIdByNickname(nickname);
    }
}
