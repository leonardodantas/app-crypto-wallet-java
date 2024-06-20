package com.crypto.wallet.domain;

import com.crypto.wallet.infra.http.responses.DigitalCurrencyAcronymResponse;

public record CryptocurrencyWallet(
        String id,
        DigitalCurrencyAcronymResponse digitalCurrencyAcronym,
        double quantity
) {
    public static CryptocurrencyWallet of(final Wallet wallet, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new CryptocurrencyWallet(wallet.getId(), DigitalCurrencyAcronymResponse.from(digitalCurrencyAcronym), wallet.getQuantity());
    }
}
