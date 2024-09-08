package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import com.crypto.wallet.infra.database.mongodb.jpa.WalletRepositorySpringData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletRepository implements IWalletRepository {

    private final WalletRepositorySpringData walletRepositorySpringData;

    @Override
    public Wallet save(final Wallet wallet) {
        try {
            final var document = walletRepositorySpringData.save(WalletDocument.from(wallet));
            return getWalletConvert(document);
        } catch (final Exception e) {
            throw new EntitySaveException(e.getMessage());
        }
    }

    @Override
    public Optional<Wallet> findByDigitalCurrencyAcronym(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronym(DigitalCurrencyAcronymDocument.from(digitalCurrencyAcronym))
                .map(WalletRepository::getWalletConvert);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepositorySpringData.findAll()
                .stream()
                .map(WalletRepository::getWalletConvert)
                .toList();
    }

    @Override
    public Optional<Wallet> findByCryptocurrencyName(final String cryptocurrency) {
        return walletRepositorySpringData.findByDigitalCurrencyAcronymName(cryptocurrency)
                .map(WalletRepository::getWalletConvert);
    }

    private static Wallet getWalletConvert(final WalletDocument walletDocument) {
        final var digitalCurrencyAcronym = new DigitalCurrencyAcronym(walletDocument.getDigitalCurrencyAcronym().getName(), walletDocument.getDigitalCurrencyAcronym().getDescription());

        return Wallet.builder()
                .id(walletDocument.getId())
                .digitalCurrencyAcronym(digitalCurrencyAcronym)
                .quantity(walletDocument.getQuantity())
                .build();
    }
}
