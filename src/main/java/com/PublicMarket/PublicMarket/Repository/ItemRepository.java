package com.PublicMarket.PublicMarket.Repository;

import com.PublicMarket.PublicMarket.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
