package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;

import java.util.List;
import java.util.Optional;

public interface IDigitalCurrencyAcronymRepository {

    List<DigitalCurrencyAcronymDocument> findAll();
    Optional<DigitalCurrencyAcronymDocument> findByName(String name);
}
