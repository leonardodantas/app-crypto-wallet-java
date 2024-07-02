package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.repositories.IWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCryptocurrencyWallet {

    private final IWalletRepository walletRepository;

    public CryptocurrencyWalletResponse getByName(final String name) {
        return walletRepository.findByCryptocurrencyName(name)
                .map(CryptocurrencyWalletResponse::from)
                .orElseThrow(() -> new CryptocurrencyNotFoundException(name));
    }

    public List<CryptocurrencyWalletResponse> getAll() {
        return walletRepository.findAll().stream()
                .map(CryptocurrencyWalletResponse::from)
                .toList();
    }
}
