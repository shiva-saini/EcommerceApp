package com.PublicMarket.PublicMarket.Repository;

import com.PublicMarket.PublicMarket.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
