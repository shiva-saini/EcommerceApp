package com.PublicMarket.PublicMarket.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // this is used for creating bot every argument constructor
@AllArgsConstructor
@NoArgsConstructor
@Builder // to build the convertor of request of response
// we can use orderRequestDto as well because it is same as that
public class CartRequestDto {
    private int productId;
    private int customerId;
    private int requiredQuantity;
}
