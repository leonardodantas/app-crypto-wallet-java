package com.crypto.wallet.app.repositories;

import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;

import java.util.List;

public interface ITickerRepository {
    List<TickerDocument> findAll();
}
