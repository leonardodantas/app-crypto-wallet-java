package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.app.usecases.IFindCryptocurrencyTrend;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyTrendController {

    private final IFindCryptocurrencyTrend findCryptocurrencyTrend;

    public FindCryptocurrencyTrendController(IFindCryptocurrencyTrend findCryptocurrencyTrend) {
        this.findCryptocurrencyTrend = findCryptocurrencyTrend;
    }

    @GetMapping("/{cryptocurrency}/trend")
    ResponseEntity<?> getFindCryptocurrencyTrendByName(@PathVariable String cryptocurrency){
        List<CryptocurrencyTrendResponse> response = this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
        return ResponseEntity.ok(response);
    }
}
