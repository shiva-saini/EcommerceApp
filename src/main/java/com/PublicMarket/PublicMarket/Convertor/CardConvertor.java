package com.PublicMarket.PublicMarket.Convertor;

import com.PublicMarket.PublicMarket.Model.Card;
import com.PublicMarket.PublicMarket.RequestDto.CardRequestDto;

public class CardConvertor {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }
}
