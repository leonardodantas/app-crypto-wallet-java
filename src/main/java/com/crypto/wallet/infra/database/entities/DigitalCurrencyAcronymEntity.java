package com.crypto.wallet.infra.database.entities;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "digital_currency_acronym")
public class DigitalCurrencyAcronymEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, length = 60)
    private String name;
    private String description;

    private DigitalCurrencyAcronymEntity(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        this.name = digitalCurrencyAcronym.getName();
        this.description = digitalCurrencyAcronym.getDescription();
    }

    public static DigitalCurrencyAcronymEntity from(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymEntity(digitalCurrencyAcronym);
    }
}
