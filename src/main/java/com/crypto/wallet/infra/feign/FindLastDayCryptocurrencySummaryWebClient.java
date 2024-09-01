package com.crypto.wallet.infra.feign;

import com.crypto.wallet.infra.controllers.jsons.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;
import com.crypto.wallet.infra.feign.json.TickerRestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummaryWebClient {

    private final WebClient webClient;

    public Mono<TickerResponse> getSummary(final DigitalCurrencyAcronymResponse digitalCurrencyAcronym) {
        final var params = new HashMap<String, String>();
        params.put("name", digitalCurrencyAcronym.getName());

        return this.webClient.get()
                .uri("/{name}/ticker", params)
                .retrieve()
                .bodyToMono(TickerRestDTO.class)
                .map(summary -> TickerResponse.of(summary, digitalCurrencyAcronym));
    }
}
