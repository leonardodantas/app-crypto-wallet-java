package com.crypto.wallet.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "digital_currency_acronym")
public class DigitalCurrencyAcronym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, length = 60)
    private String name;
    @Column(length = 120)
    private String description;

}
