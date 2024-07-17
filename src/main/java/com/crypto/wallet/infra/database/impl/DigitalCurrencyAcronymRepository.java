package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.infra.database.DigitalCurrencyAcronymSpringData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DigitalCurrencyAcronymRepository implements IDigitalCurrencyAcronymRepository {

    private final DigitalCurrencyAcronymSpringData springData;

    @Override
    public List<DigitalCurrencyAcronym> findAll() {
        return springData.findAll().stream().map(entity -> DigitalCurrencyAcronym.of(entity.getName(), entity.getDescription())).toList();
    }

    @Override
    public Optional<DigitalCurrencyAcronym> findByName(final String name) {
        return springData.findByName(name).map(entity -> DigitalCurrencyAcronym.of(entity.getName(), entity.getDescription()));
    }
}
