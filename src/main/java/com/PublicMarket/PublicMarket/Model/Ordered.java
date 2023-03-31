package com.PublicMarket.PublicMarket.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="orders")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalCost;

    private String cardUsedForPayment;

    @CreationTimestamp
    private Date orderDate;

    private int deliveryCharge;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item> orderedItems = new ArrayList<>();

}
