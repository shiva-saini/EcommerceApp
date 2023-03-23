package com.PublicMarket.PublicMarket.Repository;

import com.PublicMarket.PublicMarket.Enum.Category;
import com.PublicMarket.PublicMarket.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
   public List<Product> findAllByCategory(Category category);
}
