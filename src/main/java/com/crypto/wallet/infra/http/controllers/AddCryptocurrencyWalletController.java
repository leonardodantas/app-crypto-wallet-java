package com.crypto.wallet.infra.http.controllers;

import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import com.crypto.wallet.domain.CryptocurrencyWallet;
import com.crypto.wallet.infra.http.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.infra.http.responses.CryptocurrencyWalletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
public class AddCryptocurrencyWalletController {

    private final AddCryptocurrencyWallet addCryptocurrencyWallet;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CryptocurrencyWalletResponse addCryptocurrency(@Valid @RequestBody final CryptocurrencyWalletRequest request) {
        final var response = addCryptocurrencyWallet.addCryptocurrency(request.name(), request.quantity());
        return CryptocurrencyWalletResponse.from(response);
    }
}
