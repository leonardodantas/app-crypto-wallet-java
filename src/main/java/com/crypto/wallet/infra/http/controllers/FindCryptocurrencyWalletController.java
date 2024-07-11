package com.crypto.wallet.infra.http.controllers;


import com.crypto.wallet.domain.CryptocurrencyWallet;
import com.crypto.wallet.app.usecases.FindCryptocurrencyWallet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyWalletController {

    private final FindCryptocurrencyWallet findCryptocurrencyWallet;

    @GetMapping("/{cryptocurrency}/wallet")
    ResponseEntity<?> getCryptocurrencyByName(@PathVariable final String cryptocurrency){
        CryptocurrencyWallet response = findCryptocurrencyWallet.getByName(cryptocurrency);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<?> getAllCryptocurrency(){
        List<CryptocurrencyWallet> response = findCryptocurrencyWallet.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
