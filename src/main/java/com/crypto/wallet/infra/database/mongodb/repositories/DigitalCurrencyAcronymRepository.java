package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.infra.database.mongodb.mongorepositories.IDigitalCurrencyAcronymMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DigitalCurrencyAcronymRepository implements IDigitalCurrencyAcronymRepository {

    private final IDigitalCurrencyAcronymMongoRepository springData;

    @Override
    public List<DigitalCurrencyAcronym> findAll() {
        return springData.findAll().stream()
                .map(document -> DigitalCurrencyAcronym.of(document.getId(), document.getName(), document.getDescription()))
                .toList();
    }

    @Override
    public Optional<DigitalCurrencyAcronym> findByName(final String name) {
        return springData.findByName(name)
                .map(document -> DigitalCurrencyAcronym.of(document.getId(), document.getName(), document.getDescription()));
    }
}
