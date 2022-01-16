package com.crypto.wallet.app.models.requests;

import com.crypto.wallet.domain.ICryptocurrencyWallet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CryptocurrencyWalletRequest implements ICryptocurrencyWallet {

    @NotBlank
    private String name;

    @NotNull @Positive
    private double quatity;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getQuatity() {
        return this.quatity;
    }
}
