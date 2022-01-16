package com.crypto.wallet.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "digital_currency_acronym")
public class DigitalCurrencyAcronym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(unique = true, length = 60)
    private String name;
    private String description;

}
