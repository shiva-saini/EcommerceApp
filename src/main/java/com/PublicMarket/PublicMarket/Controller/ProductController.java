package com.PublicMarket.PublicMarket.Controller;

import com.PublicMarket.PublicMarket.Enum.Category;
import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import com.PublicMarket.PublicMarket.RequestDto.ProductRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.ProductResponseDto;
import com.PublicMarket.PublicMarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            return productService.addProduct(productRequestDto);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getAllProductsByCategory")
    public List<ProductResponseDto> getAllProductsByCategory(@RequestParam Category category){
        List<ProductResponseDto> productsList = productService.getAllProductsByCategory(category);
        return productsList;
    }
}
