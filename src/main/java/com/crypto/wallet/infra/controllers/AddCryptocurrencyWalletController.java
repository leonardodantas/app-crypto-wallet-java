package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.IAddCryptocurrencyWallet;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crypto")
public class AddCryptocurrencyWalletController {

    private final IAddCryptocurrencyWallet addCryptocurrencyWallet;

    public AddCryptocurrencyWalletController(IAddCryptocurrencyWallet addCryptocurrencyWallet) {
        this.addCryptocurrencyWallet = addCryptocurrencyWallet;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCryptocurrency(@Valid @RequestBody CryptocurrencyWalletRequest body) {
        CryptocurrencyWalletResponse response = addCryptocurrencyWallet.addCryptocurrency(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
