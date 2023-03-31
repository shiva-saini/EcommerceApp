package com.PublicMarket.PublicMarket.Service;

import com.PublicMarket.PublicMarket.Convertor.CardConvertor;
import com.PublicMarket.PublicMarket.Exception.CustomerNotFoundException;
import com.PublicMarket.PublicMarket.Model.Card;
import com.PublicMarket.PublicMarket.Model.Customer;
import com.PublicMarket.PublicMarket.Repository.CardRepository;
import com.PublicMarket.PublicMarket.Repository.CustomerRepository;
import com.PublicMarket.PublicMarket.RequestDto.CardRequestDto;
import com.PublicMarket.PublicMarket.RequestDto.CustomerRequestDto;
import com.PublicMarket.PublicMarket.ResponseDto.CardDto;
import com.PublicMarket.PublicMarket.ResponseDto.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer;

        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        // Make a card object
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .customer(customer)
                .build();

        // add the card to current card list of customer
        customer.getCards().add(card);

        customerRepository.save(customer); // save both customer and card

        // prepare Response Dto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setCustomerName(customer.getName());

        // convert every card to cardDto
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1: customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        cardResponseDto.setCardDtos(cardDtoList);
        return cardResponseDto;









        //        Customer customer;
//        try{
//            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
//        }catch (Exception e){
//            throw new RuntimeException("Invalid Customer Id");
//        }
//
//        Card card = CardConvertor.CardRequestDtoToCard(cardRequestDto);
//        card.setCustomer(customer);
//        customer.getCards().add(card);
//        customerRepository.save(customer);
//       CardResponseDto cardResponseDto = new CardResponseDto();
//       cardResponseDto.setCustomerName(customer.getName());
//       List<CardDto> cardDtos = new ArrayList<>();
//
//       for(Card card1: customer.getCards()){
//           CardDto cardDto = new CardDto();
//           cardDto.setCardType(card1.getCardType());
//           cardDto.setCardNo(card1.getCardNo());
//           cardDtos.add(cardDto);
//       }
//       cardResponseDto.setCardDtos(cardDtos);
//       return cardResponseDto;
    }
}
