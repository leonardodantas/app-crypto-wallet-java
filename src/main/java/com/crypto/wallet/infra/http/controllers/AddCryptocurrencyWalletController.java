package com.crypto.wallet.infra.http.controllers;

import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import com.crypto.wallet.infra.http.requests.CryptocurrencyWalletRequest;
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
    public ResponseEntity<?> addCryptocurrency(@Valid @RequestBody final CryptocurrencyWalletRequest request) {
        CryptocurrencyWalletResponse response = addCryptocurrencyWallet.addCryptocurrency(request.name(), request.quantity());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
