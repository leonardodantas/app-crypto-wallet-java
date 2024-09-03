package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.domain.CryptocurrencyWallet;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCryptocurrencyWallet {

    private final IWalletRepository walletRepository;

    public CryptocurrencyWallet getByName(final String name) {
        final WalletDocument wallet = walletRepository.findByCryptocurrencyName(name)
                .orElseThrow(() -> new CryptocurrencyNotFoundException(name));
        return CryptocurrencyWallet.from(wallet);
    }

    public List<CryptocurrencyWallet> getAll() {
        final List<WalletDocument> wallet = walletRepository.findAll();
        return wallet.stream().map(CryptocurrencyWallet::from).toList();
    }
}
