package com.crypto.wallet.app.models.responses;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import lombok.Getter;

@Getter
public class CryptocurrencyWalletResponse {

    private final String id;
    private final DigitalCurrencyAcronymResponse digitalCurrencyAcronym;
    private final double quantity;

    private CryptocurrencyWalletResponse(Wallet wallet, DigitalCurrencyAcronym digitalCurrencyAcronym) {
        this.id = wallet.getId();
        this.digitalCurrencyAcronym = DigitalCurrencyAcronymResponse.from(digitalCurrencyAcronym);
        this.quantity = wallet.getQuantity();
    }

    private CryptocurrencyWalletResponse(Wallet wallet) {
        this.id = wallet.getId();
        this.digitalCurrencyAcronym = DigitalCurrencyAcronymResponse.from(wallet.getDigitalCurrencyAcronym());
        this.quantity = wallet.getQuantity();
    }

    public static CryptocurrencyWalletResponse of(Wallet wallet, DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new CryptocurrencyWalletResponse(wallet, digitalCurrencyAcronym);
    }

    public static CryptocurrencyWalletResponse from(Wallet wallet) {
        return new CryptocurrencyWalletResponse(wallet);
    }
}
