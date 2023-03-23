package com.PublicMarket.PublicMarket.Model;

import com.PublicMarket.PublicMarket.Enum.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String cardNo;
    private int cvv;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;

}
