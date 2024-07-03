package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
public class AddCryptocurrencyWalletController {

    private final AddCryptocurrencyWallet addCryptocurrencyWallet;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCryptocurrency(@Valid @RequestBody CryptocurrencyWalletRequest body) {
        CryptocurrencyWalletResponse response = addCryptocurrencyWallet.addCryptocurrency(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
