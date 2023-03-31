package com.PublicMarket.PublicMarket.Convertor;


import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import com.PublicMarket.PublicMarket.Model.Product;
import com.PublicMarket.PublicMarket.Repository.SellerRepository;
import com.PublicMarket.PublicMarket.RequestDto.ProductRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConvertor {
    @Autowired
    SellerRepository sellerRepository;
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return  Product.builder().
                 productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .category(productRequestDto.getCategory())
                .build();

    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .price(product.getPrice())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
