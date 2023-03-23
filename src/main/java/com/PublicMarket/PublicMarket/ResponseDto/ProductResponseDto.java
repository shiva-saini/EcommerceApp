package com.PublicMarket.PublicMarket.ResponseDto;

import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private int price;
    private String name;
    private int quantity;
    private ProductStatus productStatus;

}
