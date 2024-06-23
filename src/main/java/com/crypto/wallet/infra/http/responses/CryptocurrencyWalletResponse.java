package com.crypto.wallet.infra.http.responses;


import com.crypto.wallet.domain.CryptocurrencyWallet;

public record CryptocurrencyWalletResponse(
        String id,
        DigitalCurrencyAcronymResponse digitalCurrencyAcronym,
        double quantity
) {
    public static CryptocurrencyWalletResponse from(final CryptocurrencyWallet cryptocurrencyWallet) {
        return null;
    }
}
