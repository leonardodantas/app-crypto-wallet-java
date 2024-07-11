package com.crypto.wallet.infra.http.responses;

import com.crypto.wallet.domain.CryptocurrencyWallet;

public record CryptocurrencyWalletResponse(
        String id,
        DigitalCurrencyAcronymResponse digitalCurrencyAcronymResponse,
        double quantity
) {

    public static CryptocurrencyWalletResponse from(final CryptocurrencyWallet cryptocurrencyWallet) {
        return new CryptocurrencyWalletResponse(cryptocurrencyWallet.getId(), DigitalCurrencyAcronymResponse.from(cryptocurrencyWallet.getDigitalCurrencyAcronym()), cryptocurrencyWallet.getQuantity());
    }
}
