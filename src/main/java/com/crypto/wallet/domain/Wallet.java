package com.crypto.wallet.domain;

import com.crypto.wallet.infra.http.responses.DigitalCurrencyAcronymResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    private String id;
    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;

    private Wallet(final DigitalCurrencyAcronym digitalCurrencyAcronym, final double quantity) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = quantity;
    }

    public static Wallet of(final DigitalCurrencyAcronym digitalCurrencyAcronym, final double quantity) {
        return new Wallet(digitalCurrencyAcronym, quantity);
    }

    public void overrideWallet(final Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
