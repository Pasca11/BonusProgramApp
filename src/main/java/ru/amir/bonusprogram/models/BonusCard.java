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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPusrchaseSum() {
        return pusrchaseSum;
    }

    public void setPusrchaseSum(int pusrchaseSum) {
        this.pusrchaseSum = pusrchaseSum;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }
}
