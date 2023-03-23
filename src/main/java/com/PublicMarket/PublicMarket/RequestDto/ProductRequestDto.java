package com.PublicMarket.PublicMarket.RequestDto;

import com.PublicMarket.PublicMarket.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    private int sellerId;
    private String name;
    private int price;
    private int quantity;
   private Category category;
}
