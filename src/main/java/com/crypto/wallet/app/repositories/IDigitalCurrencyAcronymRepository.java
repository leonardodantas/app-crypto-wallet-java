package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;

import java.util.List;
import java.util.Optional;

public interface IDigitalCurrencyAcronymRepository {

    List<DigitalCurrencyAcronymEntity> findAll();
    Optional<DigitalCurrencyAcronym> findByName(final String name);
}
