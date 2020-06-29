package com.Labs.Patterns.dao;

import com.Labs.Patterns.dto.Printer;
import com.Labs.Patterns.dto.User;
import com.Labs.Patterns.mapper.PrintersMapper;
import com.Labs.Patterns.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class PrintersDao extends JdbcDaoSupport implements IPrintersDao {

    @Autowired
    public PrintersDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Printer> findAllPrinters() {
        try
        {
         String sql="select id_printer,manufacturer,model,printing_technologies,black_and_white_print,price from printers\n" +
                 "inner join manufacturers on fk_id_manufacturer=id_manufacturer\n" +
                 "inner join printing_technologies on fk_id_printing_technology=id_printing_technology";
            List<Printer>printers=this.getJdbcTemplate().query(sql,new PrintersMapper());
         return printers;
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Printer> findPrintersByManufacturer(String manufacturer) {
        String sql="select id_printer, manufacturer,model,printing_technologies,black_and_white_print,price from printers\n" +
                "inner join manufacturers on fk_id_manufacturer=id_manufacturer\n" +
                "inner join printing_technologies on fk_id_printing_technology=id_printing_technology\n" +
                "where manufacturer=?";
        Object[]params=new Object[]{manufacturer};
        List<Printer>printers=this.getJdbcTemplate().query(sql,new PrintersMapper(),params);
        return printers;
    }

    @Override
    public void insertPrinterIntoCart(int idUser, int idPrinter) {
        String sql="insert into cart values (?,?)";
        Object[]params=new Object[]{idUser,idPrinter};
        getJdbcTemplate().update(sql,params);
    }
}
