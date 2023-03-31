package com.PublicMarket.PublicMarket.ResponseDto;

import com.PublicMarket.PublicMarket.Enum.Category;
import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {
    private String name;
    private int price;
    private Category category;
    private ProductStatus productStatus;
}
