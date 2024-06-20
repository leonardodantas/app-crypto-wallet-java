package com.crypto.wallet.infra.http.controllers;

import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.app.usecases.impl.FindCryptocurrencyTrend;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyTrendController {

    private final FindCryptocurrencyTrend findCryptocurrencyTrend;

    @GetMapping("/{cryptocurrency}/trend")
    @ResponseStatus(HttpStatus.OK)
    public List<CryptocurrencyTrendResponse> getFindCryptocurrencyTrendByName(@PathVariable final String cryptocurrency) {
        return this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
    }
}
