package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import com.crypto.wallet.infra.database.WalletRepositorySpringData;
import com.crypto.wallet.infra.database.converters.WalletConverter;
import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;
import com.crypto.wallet.infra.database.entities.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletRepository implements IWalletRepository {

    private final WalletRepositorySpringData walletRepositorySpringData;
    private final WalletConverter walletConverterConverter;

    @Override
    public Wallet save(final Wallet wallet) {
        try {
            final var walletSave = walletRepositorySpringData.save(WalletEntity.from(wallet));
            return walletConverterConverter.convert(walletSave);
        } catch (final Exception e) {
            throw new EntitySaveException("Error save Wallet");
        }
    }

    @Override
    public Optional<Wallet> findByDigitalCurrencyAcronym(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronym(DigitalCurrencyAcronymEntity.from(digitalCurrencyAcronym))
                .map(walletConverterConverter::convert);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepositorySpringData.findAll()
                .stream()
                .map(walletConverterConverter::convert)
                .toList();
    }

    @Override
    public Optional<Wallet> findByCryptocurrencyName(final String cryptocurrency) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronymName(cryptocurrency)
                .map(walletConverterConverter::convert);
    }
}
