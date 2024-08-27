package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.infra.controllers.jsons.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.app.usecases.FindCryptocurrencyTrend;
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
        return this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
    }
}
