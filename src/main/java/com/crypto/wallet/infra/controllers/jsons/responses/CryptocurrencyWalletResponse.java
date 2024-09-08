package com.crypto.wallet.infra.controllers.jsons.responses;

import com.crypto.wallet.domain.CryptocurrencyWallet;

public record CryptocurrencyWalletResponse(
        DigitalCurrencyAcronymResponse digitalCurrencyAcronym,
        double quantity
) {

    public static CryptocurrencyWalletResponse from(final CryptocurrencyWallet cryptocurrencyWallet) {
        return new CryptocurrencyWalletResponse(DigitalCurrencyAcronymResponse.from(cryptocurrencyWallet.getDigitalCurrencyAcronym()), cryptocurrencyWallet.getQuantity());
    }
}
