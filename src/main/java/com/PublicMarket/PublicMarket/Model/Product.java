package com.PublicMarket.PublicMarket.Model;

import com.PublicMarket.PublicMarket.Enum.Category;
import com.PublicMarket.PublicMarket.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    Category category;

   @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;

    @ManyToOne
    @JoinColumn
    Seller seller;
}
