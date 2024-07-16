package com.crypto.wallet.infra.http.controllers;

import com.crypto.wallet.app.usecases.FindCryptocurrencyTrend;
import com.crypto.wallet.infra.http.responses.CryptocurrencyTrendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyTrendController {

    private final FindCryptocurrencyTrend findCryptocurrencyTrend;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cryptocurrency}/trend")
    public List<CryptocurrencyTrendResponse> getFindCryptocurrencyTrendByName(@PathVariable final String cryptocurrency) {
        final var response = this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
        return response.stream().map(CryptocurrencyTrendResponse::from).toList();
    }
}
