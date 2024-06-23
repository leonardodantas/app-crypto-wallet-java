package com.crypto.wallet.infra.database;

import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;
import com.crypto.wallet.infra.database.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepositorySpringData extends JpaRepository<WalletEntity, String> {
    Optional<WalletEntity> findByDigitalCurrencyAcronym(DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity);
    Optional<WalletEntity> findByDigitalCurrencyAcronymName(String coin);
}
