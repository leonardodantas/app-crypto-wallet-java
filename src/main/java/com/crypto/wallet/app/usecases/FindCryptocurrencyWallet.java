package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.infra.controllers.jsons.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.domain.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCryptocurrencyWallet {

    private final IWalletRepository walletRepository;

    public CryptocurrencyWalletResponse getByName(final String name) {
        final Wallet wallet = walletRepository.findByCryptocurrencyName(name)
                .orElseThrow(() -> new CryptocurrencyNotFoundException(name));
        return CryptocurrencyWalletResponse.from(wallet);
    }

    public List<CryptocurrencyWalletResponse> getAll() {
        final List<Wallet> wallet = walletRepository.findAll();
        return wallet.stream().map(CryptocurrencyWalletResponse::from).toList();
    }
}
