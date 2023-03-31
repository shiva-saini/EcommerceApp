package com.PublicMarket.PublicMarket.Controller;

import com.PublicMarket.PublicMarket.RequestDto.OrderRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.OrderResponseDto;
import com.PublicMarket.PublicMarket.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponseDto;
        try{
            orderResponseDto = orderService.placeOrder(orderRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
//            return new RequestEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResponseDto,HttpStatus.ACCEPTED);

    }
}
