package com.crypto.wallet.infra.http.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CryptocurrencyWalletRequest(
        @NotBlank
        String name,
        @Positive
        double quantity
) {
}
