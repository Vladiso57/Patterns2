package com.Labs.Patterns.mapper;

import com.Labs.Patterns.dto.Printer;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintersMapper implements RowMapper {

    @Override
    public Printer mapRow(ResultSet resultSet, int i) throws SQLException {
        String manufacturer= resultSet.getString("manufacturer");
        String printingTechnology=resultSet.getString("printing_technologies");
        int speedPrint=resultSet.getInt("black_and_white_print");
        int price=resultSet.getInt("price");
        String model=resultSet.getString("model");
        int idPrinter=resultSet.getInt("id_printer");

        return new Printer(manufacturer,printingTechnology,speedPrint,price,model,idPrinter);
    }
}

