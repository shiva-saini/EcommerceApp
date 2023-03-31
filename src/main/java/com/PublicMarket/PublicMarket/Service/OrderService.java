package com.PublicMarket.PublicMarket.Service;

import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import com.PublicMarket.PublicMarket.Exception.CustomerNotFoundException;
import com.PublicMarket.PublicMarket.Exception.ProductNotFoundException;
import com.PublicMarket.PublicMarket.Model.*;
import com.PublicMarket.PublicMarket.Repository.CustomerRepository;
import com.PublicMarket.PublicMarket.Repository.ItemRepository;
import com.PublicMarket.PublicMarket.Repository.OrderedRepository;
import com.PublicMarket.PublicMarket.Repository.ProductRepository;
import com.PublicMarket.PublicMarket.RequestDto.OrderRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.ItemResponseDto;
import com.PublicMarket.PublicMarket.ResponseDto.OrderResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemService itemService;


    @Autowired
    JavaMailSender emailSender;

    @Autowired
    ProductRepository productRepository;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
          // not in this api customer,product,item and ordered are used so we will update all of these
        // one by one in that way we will be better able to set the things
       // this is direct order functionality
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDto.getRequiredQuantity()* product.getPrice());
        order.setDeliveryCharge(40);
        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);
        order.getOrderedItems().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getProductName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        // send an email
        String text = "Congrats your order with total value "+order.getTotalCost()+" has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backendavengers@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed Notification");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDto;


    }
}
