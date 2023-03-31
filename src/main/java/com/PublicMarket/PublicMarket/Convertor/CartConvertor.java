package com.PublicMarket.PublicMarket.Convertor;

import com.PublicMarket.PublicMarket.Model.Item;
import com.PublicMarket.PublicMarket.ResponseDto.CartResponseDto;

public class CartConvertor {
    public static CartResponseDto itemToResponse(Item item){
       return CartResponseDto.builder()
                .requiredQuantity(item.getRequiredQuantity())
                .productName(item.getName())
                .productPrice(item.getProduct().getPrice())
                .build();
    }
}
