package com.Labs.Patterns.service;

import com.Labs.Patterns.dao.IPrintersDao;
import com.Labs.Patterns.dto.Printer;
import com.Labs.Patterns.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PrintersService implements IPrintersService {
    private List<Printer>list;

    @Autowired
    IPrintersDao iPrintersDao;

    @Override
    public Page<Printer> getAllPrinters(Pageable pageable) {
        List<Printer>printers=iPrintersDao.findAllPrinters();
        int pageSize=pageable.getPageSize();
        int currentPage=pageable.getPageNumber();
        int startItem=currentPage*pageSize;
        if(printers.size()<startItem)
        {
            list= Collections.emptyList();
        }
        else
        {
            int toIndex=Math.min(startItem+pageSize,printers.size());
            list=printers.subList(startItem,toIndex);
        }
        Page<Printer>printersPage=new PageImpl<Printer>(list, PageRequest.of(currentPage,pageSize),printers.size());
        return printersPage;
    }

    @Override
    public Page<Printer> getPrintersByManufacturer(String manufacturer,Pageable pageable) {
        List<Printer> printers = iPrintersDao.findPrintersByManufacturer(manufacturer);
        if (printers.size()!=0) {
            int pageSize = pageable.getPageSize();
            int currentPage = pageable.getPageNumber();
            int startItem = currentPage * pageSize;
            if (printers.size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, printers.size());
                list = printers.subList(startItem, toIndex);
            }
            Page<Printer> printersPage = new PageImpl<Printer>(list, PageRequest.of(currentPage, pageSize), printers.size());
            return printersPage;
        } else {
            return null;
        }
    }

    @Override
    public void addToCart(int idUser, int idPrinter) {
        this.iPrintersDao.insertPrinterIntoCart(idUser,idPrinter);
    }
}
