package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.app.usecases.IFindCryptocurrencyWallet;
import com.crypto.wallet.infra.database.entities.WalletEntity;
import com.crypto.wallet.infra.http.responses.CryptocurrencyWalletResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindCryptocurrencyWallet implements IFindCryptocurrencyWallet {

    private final IWalletRepository walletRepository;

    public FindCryptocurrencyWallet(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public CryptocurrencyWalletResponse getByName(String name) {
        WalletEntity wallet = walletRepository.findByCryptocurrencyName(name)
                .orElseThrow(() -> new CryptocurrencyNotFoundException(name));
        return CryptocurrencyWalletResponse.from(wallet);
    }

    @Override
    public List<CryptocurrencyWalletResponse> getAll() {
        List<WalletEntity> wallet = walletRepository.findAll();
        return wallet.stream().map(CryptocurrencyWalletResponse::from).collect(Collectors.toUnmodifiableList());
    }
}
