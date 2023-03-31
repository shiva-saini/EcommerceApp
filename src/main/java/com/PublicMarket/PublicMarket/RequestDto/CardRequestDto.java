package com.PublicMarket.PublicMarket.RequestDto;

import com.PublicMarket.PublicMarket.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardRequestDto {
    private String cardNo;
    private int cvv;
    private int customerId;
    private CardType cardType;
}
