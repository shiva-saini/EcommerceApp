package com.PublicMarket.PublicMarket.Controller;

import com.PublicMarket.PublicMarket.Exception.ProductNotFoundException;
import com.PublicMarket.PublicMarket.Model.Item;
import com.PublicMarket.PublicMarket.ResponseDto.ItemResponseDto;
import com.PublicMarket.PublicMarket.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @GetMapping("/view/{productId}")
    public ResponseEntity viewProduct(@PathVariable("productId") int productId) throws ProductNotFoundException {
        ItemResponseDto itemResponseDto;
        try{
            itemResponseDto = itemService.viewProduct(productId);
        }catch (ProductNotFoundException e){
            return new ResponseEntity(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(itemResponseDto,HttpStatus.ACCEPTED);
    }
}
