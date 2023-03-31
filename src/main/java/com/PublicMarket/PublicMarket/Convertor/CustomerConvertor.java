package com.PublicMarket.PublicMarket.Convertor;

import com.PublicMarket.PublicMarket.Model.Customer;
import com.PublicMarket.PublicMarket.RequestDto.CustomerRequestDto;

public class CustomerConvertor {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .build();
    }
}
