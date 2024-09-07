package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.Ticker;

import java.util.List;

public interface ITickerRepository {
    List<Ticker> findAll();
}
