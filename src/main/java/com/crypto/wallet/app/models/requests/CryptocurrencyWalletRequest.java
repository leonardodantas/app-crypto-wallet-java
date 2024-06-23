package com.crypto.wallet.app.models.requests;

import com.crypto.wallet.domain.ICryptocurrencyWallet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CryptocurrencyWalletRequest implements ICryptocurrencyWallet {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private double quantity;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getQuantity() {
        return this.quantity;
    }
}
