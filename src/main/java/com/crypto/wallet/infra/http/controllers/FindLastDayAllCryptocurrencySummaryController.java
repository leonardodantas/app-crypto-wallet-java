package com.crypto.wallet.infra.http.controllers;

import com.crypto.wallet.app.usecases.FindLastDayCryptocurrencySummary;
import com.crypto.wallet.infra.http.responses.TickerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lastday/cryptocurrency/summary")
public class FindLastDayAllCryptocurrencySummaryController {

    private final FindLastDayCryptocurrencySummary findLastDayCryptocurrencySummary;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TickerResponse> getSummaryCryptocurrency() {
        return findLastDayCryptocurrencySummary.getAllTicker()
                .stream()
                .map(TickerResponse::from)
                .toList();
    }
}
