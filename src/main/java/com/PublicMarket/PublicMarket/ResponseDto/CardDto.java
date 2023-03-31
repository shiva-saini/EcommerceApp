package com.PublicMarket.PublicMarket.ResponseDto;

import com.PublicMarket.PublicMarket.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private String cardNo;
    private CardType cardType;
}
