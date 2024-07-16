package com.crypto.wallet.infra.http.controllers;


import com.crypto.wallet.app.usecases.FindCryptocurrencyWallet;
import com.crypto.wallet.infra.http.responses.CryptocurrencyWalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        final var response = findCryptocurrencyWallet.getByName(cryptocurrency);
        return CryptocurrencyWalletResponse.from(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CryptocurrencyWalletResponse> getAllCryptocurrency() {
        return findCryptocurrencyWallet.getAll()
                .stream()
                .map(CryptocurrencyWalletResponse::from)
                .toList();
    }
}
