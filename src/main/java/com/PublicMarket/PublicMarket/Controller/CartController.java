package com.PublicMarket.PublicMarket.Controller;

import com.PublicMarket.PublicMarket.RequestDto.CartRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.CartResponseDto;
import com.PublicMarket.PublicMarket.ResponseDto.OrderResponseDto;
import com.PublicMarket.PublicMarket.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/addToCart")
    public CartResponseDto addToCart(@RequestBody CartRequestDto cartRequestDto) throws Exception {
        CartResponseDto cartResponseDto;
        try{
            cartResponseDto = cartService.addToCart(cartRequestDto);
        }catch (Exception e){
            throw new Exception("Something Is Worg with details");
        }
       return cartResponseDto;
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkoutCart(@PathVariable("customerId") int customerId){
        List<OrderResponseDto> orderResponseDtos;
        try{
            orderResponseDtos = cartService.checkOut(customerId);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResponseDtos,HttpStatus.ACCEPTED);
    }
}
