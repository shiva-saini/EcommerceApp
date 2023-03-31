package com.PublicMarket.PublicMarket.Service;

import com.PublicMarket.PublicMarket.Convertor.CustomerConvertor;
import com.PublicMarket.PublicMarket.Model.Cart;
import com.PublicMarket.PublicMarket.Model.Customer;
import com.PublicMarket.PublicMarket.Repository.CustomerRepository;
import com.PublicMarket.PublicMarket.RequestDto.CustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public String addCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = CustomerConvertor.customerRequestDtoToCustomer(customerRequestDto);
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);
        customerRepository.save(customer);
        return "Added successfully";

    }
}
