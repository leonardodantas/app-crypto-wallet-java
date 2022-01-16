package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import com.crypto.wallet.infra.database.WalletRepositorySpringData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WalletRepository implements IWalletRepository {

    private final WalletRepositorySpringData walletRepositorySpringData;

    public WalletRepository(WalletRepositorySpringData walletRepositorySpringData) {
        this.walletRepositorySpringData = walletRepositorySpringData;
    }

    @Override
    public Wallet save(Wallet wallet) {
        try {
            return walletRepositorySpringData.save(wallet);
        } catch (Exception e){
            throw new EntitySaveException(e.getMessage());
        }
    }

    @Override
    public Optional<Wallet> findByDigitalCurrencyAcronym(DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronym(digitalCurrencyAcronym);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepositorySpringData.findAll();
    }

    @Override
    public Optional<Wallet> findByCryptocurrencyName(String cryptocurrency) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronymName(cryptocurrency);
    }
}
