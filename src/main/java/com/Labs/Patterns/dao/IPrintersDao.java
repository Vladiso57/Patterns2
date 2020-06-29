package com.Labs.Patterns.dao;

import com.Labs.Patterns.dto.Printer;
import com.Labs.Patterns.dto.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPrintersDao {
    List<Printer>findAllPrinters();
    List<Printer>findPrintersByManufacturer(String manufacturer);
    void insertPrinterIntoCart(int idUser, int idPrinter);
}
