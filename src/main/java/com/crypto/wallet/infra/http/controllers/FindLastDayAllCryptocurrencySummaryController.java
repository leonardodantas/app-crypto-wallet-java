package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.models.responses.TickerResponse;
import com.crypto.wallet.app.usecases.FindLastDayCryptocurrencySummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lastday/cryptocurrency/summary")
public class FindLastDayAllCryptocurrencySummaryController {

    private final FindLastDayCryptocurrencySummary findLastDayCryptocurrencySummary;

    @GetMapping
    public ResponseEntity<?> getSummaryCryptocurrencyripto() {
        List<TickerResponse> response = findLastDayCryptocurrencySummary.getAllTicker();
        return ResponseEntity.ok(response);
    }
}
