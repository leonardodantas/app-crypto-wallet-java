package com.crypto.wallet.infra.database;

import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalCurrencyAcronymSpringData extends JpaRepository<DigitalCurrencyAcronymEntity, String> {
    Optional<DigitalCurrencyAcronymEntity> findByName(final String name);
}
