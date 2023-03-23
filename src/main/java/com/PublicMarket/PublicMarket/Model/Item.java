package com.PublicMarket.PublicMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int requiredQuantity;

    @OneToOne
    @JoinColumn
    Product product;

    @ManyToOne
    @JoinColumn
    Cart cart;


    @ManyToOne
    @JoinColumn
    Ordered order;
}
