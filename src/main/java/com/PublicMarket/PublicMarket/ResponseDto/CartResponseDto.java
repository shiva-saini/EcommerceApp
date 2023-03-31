package com.PublicMarket.PublicMarket.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartResponseDto {
    private String productName;
    private int productPrice;
    private int requiredQuantity;

}
