package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.repositories.ITickerRepository;
import com.crypto.wallet.domain.Ticker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummary {

    private final ITickerRepository tickerRepository;

    public List<Ticker> getAllTicker() {
        return tickerRepository.findAll().stream().map(Ticker::from).toList();
    }

}
