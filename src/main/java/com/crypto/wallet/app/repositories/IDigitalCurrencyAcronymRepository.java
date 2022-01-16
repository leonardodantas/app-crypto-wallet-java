package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;

import java.util.List;
import java.util.Optional;

public interface IDigitalCurrencyAcronymRepository {

    List<DigitalCurrencyAcronym> findAll();
    Optional<DigitalCurrencyAcronym> findByName(String name);
}
