package com.Labs.Patterns.service;

import com.Labs.Patterns.dao.ICartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartDao iCartDao;

    @Override
    public boolean isPrinterInCartExist(int idUser) {
        int result=this.iCartDao.getPrinterInCart(idUser);
        if(result>=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
