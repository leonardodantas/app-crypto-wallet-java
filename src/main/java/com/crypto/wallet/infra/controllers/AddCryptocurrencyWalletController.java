package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
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
    public CryptocurrencyWalletResponse addCryptocurrency(@Valid @RequestBody final CryptocurrencyWalletRequest body) {
        return addCryptocurrencyWallet.addCryptocurrency(body);
    }
}
