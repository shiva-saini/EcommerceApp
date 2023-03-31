package com.PublicMarket.PublicMarket.Exception;

import com.PublicMarket.PublicMarket.Repository.ProductRepository;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message){
        super(message);
    }
}
