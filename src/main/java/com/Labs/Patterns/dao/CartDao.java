package com.Labs.Patterns.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CartDao extends JdbcDaoSupport implements ICartDao {

    @Autowired
    public CartDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public int getPrinterInCart(int idUser) {
        String sql="select fk_id_printer from cart where fk_id_user=?";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{idUser},Integer.class);
    }
}
