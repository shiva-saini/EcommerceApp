package com.PublicMarket.PublicMarket.Repository;

import com.PublicMarket.PublicMarket.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
