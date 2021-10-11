package com.sg.account.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
public class Account {

    @Id
    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "account_balance", unique = false, nullable = false)
    private Double accountBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", unique = false, nullable = false)
    @ToString.Exclude
    private Currency currency;

    public Account(String id, Double accountBalance, Currency currency) {
        this.id = id;
        this.accountBalance = accountBalance;
        this.currency = currency;
    }

}
