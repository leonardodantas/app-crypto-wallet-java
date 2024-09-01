package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.repositories.ITickerRepository;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummary {

    private final ITickerRepository tickerRepository;

    public List<TickerResponse> getAllTicker() {
        return tickerRepository.findAll().stream().map(TickerResponse::from).toList();
    }

}
