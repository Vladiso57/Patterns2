package com.Labs.Patterns.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RoleDao extends JdbcDaoSupport implements IRoleDao {

    @Autowired
    public RoleDao(DataSource dataSource)
    {
        this.setDataSource(dataSource);
    }

    @Override
    public List<String> getRoles(String nickname) {
        String sql="select role from roles inner join users on id_role=fk_id_role where nickname=?";
        Object[]params=new Object[]{nickname};
        List<String>roles=this.getJdbcTemplate().queryForList(sql,params,String.class);
        return roles;
    }
}
