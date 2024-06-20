package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;
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
    public List<DigitalCurrencyAcronymEntity> findAll() {
        return springData.findAll();
    }

    @Override
    public Optional<DigitalCurrencyAcronymEntity> findByName(String name) {
        return springData.findByName(name);
    }
}
