package com.Labs.Patterns.service;

import com.Labs.Patterns.dto.Printer;
import com.Labs.Patterns.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPrintersService {
    Page<Printer> getAllPrinters(Pageable pageable);
    Page<Printer>getPrintersByManufacturer(String manufacturer,Pageable pageable);
    void addToCart(int idUser, int idPrinter);
}
