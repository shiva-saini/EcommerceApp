package com.PublicMarket.PublicMarket.Service;

import com.PublicMarket.PublicMarket.Convertor.ItemConvertor;
import com.PublicMarket.PublicMarket.Exception.ProductNotFoundException;
import com.PublicMarket.PublicMarket.Model.Item;
import com.PublicMarket.PublicMarket.Model.Product;
import com.PublicMarket.PublicMarket.Repository.ProductRepository;
import com.PublicMarket.PublicMarket.ResponseDto.ItemResponseDto;
import com.PublicMarket.PublicMarket.ResponseDto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;
    public ItemResponseDto viewProduct(int prodcutId) throws ProductNotFoundException {
          Product product;
          try{
              product = productRepository.findById(prodcutId).get();
          }catch (Exception e){
              throw new ProductNotFoundException("Invalid Product Id");
          }

          Item item = ItemConvertor.prodcutToItem(product);

          //map item to product
          product.setItem(item);
          productRepository.save(product);
          ItemResponseDto itemResponseDto = ItemConvertor.productToItemResponseDto(product);
          return itemResponseDto;
    }
}
