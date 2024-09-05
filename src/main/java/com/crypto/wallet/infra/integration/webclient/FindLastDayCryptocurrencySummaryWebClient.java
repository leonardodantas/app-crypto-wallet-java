package com.crypto.wallet.infra.integration.webclient;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import com.crypto.wallet.infra.integration.json.TickerRestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummaryWebClient {

    private final WebClient webClient;

    public Mono<Ticker> getSummary(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        final var params = new HashMap<String, String>();
        params.put("name", digitalCurrencyAcronym.getName());

        return this.webClient.get()
                .uri("/{name}/ticker", params)
                .retrieve()
                .bodyToMono(TickerRestDTO.class)
                .map(summary -> Ticker.of(summary, digitalCurrencyAcronym));
    }
}
