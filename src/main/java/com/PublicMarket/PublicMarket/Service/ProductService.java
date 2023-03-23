package com.PublicMarket.PublicMarket.Service;


import com.PublicMarket.PublicMarket.Convertor.ProductConvertor;
import com.PublicMarket.PublicMarket.Enum.Category;
import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import com.PublicMarket.PublicMarket.Exception.SellerNotFoundException;
import com.PublicMarket.PublicMarket.Model.Product;
import com.PublicMarket.PublicMarket.Model.Seller;
import com.PublicMarket.PublicMarket.Repository.ProductRepository;
import com.PublicMarket.PublicMarket.Repository.SellerRepository;
import com.PublicMarket.PublicMarket.RequestDto.ProductRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        // first of all i need to check seller with id (valid or not)
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch(Exception e){
            throw new SellerNotFoundException("Invalid Seller Id");
        }
        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);
//        productRepository.save(product); dont need to save because parent seller in saved
        sellerRepository.save(seller);
        return ProductConvertor.productToProductResponseDto(product);

    }

    public List<ProductResponseDto> getAllProductsByCategory(Category category){
        List<Product> allproducts = productRepository.findAllByCategory(category);
        List<ProductResponseDto> allProductsWithResponseDto = new ArrayList<>();
        for(Product product:allproducts){
            ProductResponseDto currProduct = ProductConvertor.productToProductResponseDto(product);
            allProductsWithResponseDto.add(currProduct);
        }
        return allProductsWithResponseDto;
    }


}
