package com.PublicMarket.PublicMarket.Convertor;

import com.PublicMarket.PublicMarket.Model.Item;
import com.PublicMarket.PublicMarket.Model.Product;
import com.PublicMarket.PublicMarket.ResponseDto.ItemResponseDto;
import com.PublicMarket.PublicMarket.ResponseDto.ProductResponseDto;

public class ItemConvertor {
    public static Item prodcutToItem(Product product){
        return Item.builder()
                .requiredQuantity(0)
                .product(product)
                .name(product.getProductName())
                .build();
    }

    public static ItemResponseDto productToItemResponseDto(Product product){
        return ItemResponseDto.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
