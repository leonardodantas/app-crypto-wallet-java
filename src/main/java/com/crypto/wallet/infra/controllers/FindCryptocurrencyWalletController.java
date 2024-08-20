package com.crypto.wallet.infra.controllers;


import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.FindCryptocurrencyWallet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyWalletController {

    private final FindCryptocurrencyWallet findCryptocurrencyWallet;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cryptocurrency}/wallet")
    public CryptocurrencyWalletResponse getCryptocurrencyByName(@PathVariable final String cryptocurrency) {
        return findCryptocurrencyWallet.getByName(cryptocurrency);
    }

    @GetMapping
    public ResponseEntity<?> getAllCryptocurrency() {
        final List<CryptocurrencyWalletResponse> response = findCryptocurrencyWallet.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
