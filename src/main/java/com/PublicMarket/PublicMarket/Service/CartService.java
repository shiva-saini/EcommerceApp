package com.PublicMarket.PublicMarket.Service;

import com.PublicMarket.PublicMarket.Convertor.CartConvertor;
import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import com.PublicMarket.PublicMarket.Exception.CustomerNotFoundException;
import com.PublicMarket.PublicMarket.Exception.ProductNotFoundException;
import com.PublicMarket.PublicMarket.Model.*;
import com.PublicMarket.PublicMarket.Repository.CustomerRepository;
import com.PublicMarket.PublicMarket.Repository.ProductRepository;
import com.PublicMarket.PublicMarket.RequestDto.CartRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.CartResponseDto;
import com.PublicMarket.PublicMarket.ResponseDto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    ProductRepository productRepository;
   public CartResponseDto addToCart(CartRequestDto cartRequestDto) throws Exception {
       Customer customer;
       try{
           customer = customerRepository.findById(cartRequestDto.getCustomerId()).get();
       }catch (Exception e){
           throw new CustomerNotFoundException("Invalid customer id");
       }
       Product product;
       try{
           product = productRepository.findById(cartRequestDto.getProductId()).get();

       }catch (Exception e){
           throw new ProductNotFoundException("Invalid Product Id");
       }
      if(product.getQuantity() < cartRequestDto.getRequiredQuantity()){
          throw new Exception("Insufficient Quantity");
      }
      Cart cart = customer.getCart();
      int costOfCart = cart.getCartTotal() + cartRequestDto.getRequiredQuantity() * product.getPrice();
      cart.setCartTotal(costOfCart);
      Item item = new Item();
      item.setProduct(product);
      item.setRequiredQuantity(cartRequestDto.getRequiredQuantity());
      item.setName(product.getProductName());
      item.setCart(cart);
      cart.getItems().add(item);

      // note we don't need to save(change) the cart because cart does not change related to a customer it is 1:1 for a customer
      // right now we are just adding to cart we are not ordering so no need to add to order
       // good practice is simply save parent
       customerRepository.save(customer);
       CartResponseDto cartResponseDto  = CartConvertor.itemToResponse(item);
       return cartResponseDto;
   }

   public List<OrderResponseDto> checkOut(int customerId) throws CustomerNotFoundException {
       Customer customer;
       try{
           customer = customerRepository.findById(customerId).get();
       }
       catch(Exception e){
           throw new CustomerNotFoundException("Invalid Customer id !!!");
       }

       List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
       int totalCost = customer.getCart().getCartTotal();
       Cart cart = customer.getCart();
       for(Item item: cart.getItems()){
           Ordered order = new Ordered();
           order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
           order.setDeliveryCharge(0);
           order.setCustomer(customer);
           order.getOrderedItems().add(item);

           Card card = customer.getCards().get(0);
           String cardNo = "";
           for(int i=0;i<card.getCardNo().length()-4;i++)
               cardNo += 'X';
           cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
           order.setCardUsedForPayment(cardNo);

           int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
           if(leftQuantity<=0)
               item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
           item.getProduct().setQuantity(leftQuantity);

           customer.getOrders().add(order);

           // prepare response dto
           OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                   .productName(item.getProduct().getProductName())
                   .orderDate(order.getOrderDate())
                   .quantityOrdered(item.getRequiredQuantity())
                   .cardUsedForPayment(cardNo)
                   .itemPrice(item.getProduct().getPrice())
                   .totalCost(order.getTotalCost())
                   .deliveryCharge(0)
                   .build();

           orderResponseDtos.add(orderResponseDto);
       }

       cart.setItems(new ArrayList<>());
       cart.setCartTotal(0);
       customerRepository.save(customer);

       String text = "Congrats your order with total value "+totalCost+" has been placed";

       SimpleMailMessage message = new SimpleMailMessage();
       message.setFrom("");
       message.setTo(customer.getEmail());
       message.setSubject("Order Placed from China Market");
       message.setText(text);
       emailSender.send(message);

       return orderResponseDtos;

   }
}
