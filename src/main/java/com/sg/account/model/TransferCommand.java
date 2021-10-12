package com.sg.account.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transfer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferCommand {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_account_id", referencedColumnName = "id")
    private Account fromAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_account_id", referencedColumnName = "id")
    private Account toAccount;

    @Column(name = "balance", unique = false, nullable = false)
    private Double balance;

    @Column(name = "date", unique = false, nullable = false)
    private OffsetDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", unique = false, nullable = false)
    @ToString.Exclude
    private Currency currency;

}
