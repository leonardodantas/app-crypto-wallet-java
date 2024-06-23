package com.crypto.wallet.infra.http.controllers;


import com.crypto.wallet.app.usecases.IFindCryptocurrencyWallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyWalletController {

    private final IFindCryptocurrencyWallet findCryptocurrencyWallet;

    public FindCryptocurrencyWalletController(IFindCryptocurrencyWallet findCryptocurrencyWallet) {
        this.findCryptocurrencyWallet = findCryptocurrencyWallet;
    }

    @GetMapping("/{cryptocurrency}/wallet")
    ResponseEntity<?> getCryptocurrencyByName(@PathVariable String cryptocurrency) {
        CryptocurrencyWalletResponse response = findCryptocurrencyWallet.getByName(cryptocurrency);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<?> getAllCryptocurrency() {
        List<CryptocurrencyWalletResponse> response = findCryptocurrencyWallet.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
