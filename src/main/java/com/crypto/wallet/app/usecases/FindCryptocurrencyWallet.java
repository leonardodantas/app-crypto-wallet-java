package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.domain.CryptocurrencyWallet;
import com.crypto.wallet.app.repositories.IWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCryptocurrencyWallet {

    private final IWalletRepository walletRepository;

    public CryptocurrencyWallet getByName(final String name) {
        return walletRepository.findByCryptocurrencyName(name)
                .map(CryptocurrencyWallet::from)
                .orElseThrow(() -> new CryptocurrencyNotFoundException(name));
    }

    public List<CryptocurrencyWallet> getAll() {
        return walletRepository.findAll().stream()
                .map(CryptocurrencyWallet::from)
                .toList();
    }
}
