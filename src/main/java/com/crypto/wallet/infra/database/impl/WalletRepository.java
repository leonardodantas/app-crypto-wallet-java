package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;
import com.crypto.wallet.infra.database.entities.WalletEntity;
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
    public WalletEntity save(WalletEntity wallet) {
        try {
            return walletRepositorySpringData.save(wallet);
        } catch (Exception e){
            throw new EntitySaveException(e.getMessage());
        }
    }

    @Override
    public Optional<WalletEntity> findByDigitalCurrencyAcronym(DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronym(digitalCurrencyAcronymEntity);
    }

    @Override
    public List<WalletEntity> findAll() {
        return walletRepositorySpringData.findAll();
    }

    @Override
    public Optional<WalletEntity> findByCryptocurrencyName(String cryptocurrency) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronymName(cryptocurrency);
    }
}
