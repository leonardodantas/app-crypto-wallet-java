package com.crypto.wallet.infra.http.controllers;

import com.crypto.wallet.app.usecases.FindCryptocurrencyTrend;
import com.crypto.wallet.infra.http.responses.CryptocurrencyTrendResponse;
import lombok.RequiredArgsConstructor;
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
    public List<CryptocurrencyTrendResponse> getFindCryptocurrencyTrendByName(@PathVariable final String cryptocurrency) {
        final var response = this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
        return response.stream().map(CryptocurrencyTrendResponse::from).toList();
    }
}
