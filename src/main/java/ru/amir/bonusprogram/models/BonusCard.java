package ru.amir.bonusprogram.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Bonus_Card")
public class BonusCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "purchases_sum")
    private int pusrchaseSum;

    @Column(name = "discount")
    private int discount;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Person customer;
}
