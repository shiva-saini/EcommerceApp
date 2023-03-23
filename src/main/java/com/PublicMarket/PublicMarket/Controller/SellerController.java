package com.PublicMarket.PublicMarket.Controller;

import com.PublicMarket.PublicMarket.Repository.SellerRepository;
import com.PublicMarket.PublicMarket.RequestDto.SellerRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.SellerResponseDto;
import com.PublicMarket.PublicMarket.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        sellerService.addSeller(sellerRequestDto);
        return "Seller added Successfully";
    }

    @GetMapping("/allSellers")
    public List<SellerResponseDto> getAllSellers(){
        return sellerService.getAllSellers();
    }

    @GetMapping("/byPanNo")
    public SellerResponseDto getByPan(@RequestParam("panNo") String panNo){
        return sellerService.getByPan(panNo);
    }

}
