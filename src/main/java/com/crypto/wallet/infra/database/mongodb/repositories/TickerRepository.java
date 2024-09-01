package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.repositories.ITickerRepository;
import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import com.crypto.wallet.infra.database.mongodb.jpa.TickerSpringData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TickerRepository implements ITickerRepository {

    private final TickerSpringData tickerSpringData;

    @Override
    public List<TickerDocument> findAll() {
        return tickerSpringData.findAll();
    }
}
