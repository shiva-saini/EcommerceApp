package com.PublicMarket.PublicMarket.Repository;

import com.PublicMarket.PublicMarket.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    public Seller findByPanNo(String panNo);
}

