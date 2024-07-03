package com.crypto.wallet.infra.http.controllers;

import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.app.usecases.FindCryptocurrencyTrend;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyTrendController {

    private final FindCryptocurrencyTrend findCryptocurrencyTrend;

    @GetMapping("/{cryptocurrency}/trend")
    ResponseEntity<?> getFindCryptocurrencyTrendByName(@PathVariable final String cryptocurrency) {
        List<CryptocurrencyTrendResponse> response = this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
        return ResponseEntity.ok(response);
    }
}
