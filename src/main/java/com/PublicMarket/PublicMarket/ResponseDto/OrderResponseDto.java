package com.PublicMarket.PublicMarket.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private String productName;
    private Date orderDate;
    private String cardUsedForPayment;
    private int totalCost;
    private int deliveryCharge;
    private int itemPrice;
    private int quantityOrdered;
}
