package com.PublicMarket.PublicMarket.Service;

import com.PublicMarket.PublicMarket.Convertor.SellerConvertor;
import com.PublicMarket.PublicMarket.Model.Seller;
import com.PublicMarket.PublicMarket.Repository.SellerRepository;
import com.PublicMarket.PublicMarket.RequestDto.SellerRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;
    public void addSeller(SellerRequestDto sellerRequestDto){
       Seller seller = SellerConvertor.sellerRequestDtoToSeller(sellerRequestDto);
       sellerRepository.save(seller);

    }

    public List<SellerResponseDto> getAllSellers(){
        List<Seller> sellers  = sellerRepository.findAll();
        List<SellerResponseDto> allsellers = new ArrayList<>();
        for(Seller seller:sellers){
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(seller);
            allsellers.add(sellerResponseDto);
        }
        return allsellers;
    }

    public SellerResponseDto getByPan(String panNo){
        Seller seller = sellerRepository.findByPanNo(panNo);
        SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(seller);
        return sellerResponseDto;
    }
}
