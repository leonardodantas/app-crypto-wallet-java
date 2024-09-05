package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import com.crypto.wallet.infra.database.mongodb.jpa.WalletRepositorySpringData;
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
    public WalletDocument save(WalletDocument wallet) {
        try {
            return walletRepositorySpringData.save(wallet);
        } catch (Exception e){
            throw new EntitySaveException(e.getMessage());
        }
    }

    @Override
    public Optional<WalletDocument> findByDigitalCurrencyAcronym(DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronym(digitalCurrencyAcronym);
    }

    @Override
    public List<WalletDocument> findAll() {
        return walletRepositorySpringData.findAll();
    }

    @Override
    public Optional<WalletDocument> findByCryptocurrencyName(String cryptocurrency) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronymName(cryptocurrency);
    }
}
