package com.PublicMarket.PublicMarket.Convertor;

import com.PublicMarket.PublicMarket.Model.Seller;
import com.PublicMarket.PublicMarket.RequestDto.SellerRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.SellerResponseDto;

public class SellerConvertor {
    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .panNo(sellerRequestDto.getPanNo())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }
}
