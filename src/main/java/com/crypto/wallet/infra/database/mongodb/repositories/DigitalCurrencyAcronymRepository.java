package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.jpa.DigitalCurrencyAcronymSpringData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DigitalCurrencyAcronymRepository implements IDigitalCurrencyAcronymRepository {

    private final DigitalCurrencyAcronymSpringData springData;

    @Override
    public List<DigitalCurrencyAcronymDocument> findAll() {
        return springData.findAll();
    }

    @Override
    public Optional<DigitalCurrencyAcronymDocument> findByName(final String name) {
        return springData.findByName(name);
    }
}
