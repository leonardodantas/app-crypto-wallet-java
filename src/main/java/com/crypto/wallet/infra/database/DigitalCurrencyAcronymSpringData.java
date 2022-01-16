package com.crypto.wallet.infra.database;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalCurrencyAcronymSpringData extends JpaRepository<DigitalCurrencyAcronym, String> {
    Optional<DigitalCurrencyAcronym> findByName(String name);
}
