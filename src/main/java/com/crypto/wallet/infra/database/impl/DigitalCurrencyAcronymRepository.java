package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.infra.database.DigitalCurrencyAcronymSpringData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DigitalCurrencyAcronymRepository implements IDigitalCurrencyAcronymRepository {

    private final DigitalCurrencyAcronymSpringData springData;

    public DigitalCurrencyAcronymRepository(DigitalCurrencyAcronymSpringData springData) {
        this.springData = springData;
    }

    @Override
    public List<DigitalCurrencyAcronym> findAll() {
        return springData.findAll();
    }

    @Override
    public Optional<DigitalCurrencyAcronym> findByName(String name) {
        return springData.findByName(name);
    }
}
