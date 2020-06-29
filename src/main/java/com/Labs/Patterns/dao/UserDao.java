package com.Labs.Patterns.dao;

import com.Labs.Patterns.dto.User;
import com.Labs.Patterns.mapper.UsersMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao extends JdbcDaoSupport implements IUserDao  {

    @Autowired
    public UserDao(DataSource dataSource)
    {
        this.setDataSource(dataSource);
    }

    @Override
    public void addUser(User user)   {

            String sql = "insert into users(nickname,password,fk_id_role) values (?,?,2)";
            getJdbcTemplate().update(sql, user.getNickname(), user.getPassword());
    }

    @Override
    public User findByNicknameAndPassword(String nickname, String password)  {
        try
        {
            String sql="select id_user,nickname,password from users where nickname=? and password=?";
            return getJdbcTemplate().queryForObject(sql,new UsersMapper(),nickname,password);
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public User findByNickname(String nickname) {
        try
        {
            String sql="select nickname,password from users where nickname=?";
            return getJdbcTemplate().queryForObject(sql, new UsersMapper(),nickname);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public int getIdByNickname(String nickname) {
            String sql = "select id_user from users where nickname=?";
            return this.getJdbcTemplate().queryForObject(sql,new Object[]{nickname},Integer.class);
    }
}
