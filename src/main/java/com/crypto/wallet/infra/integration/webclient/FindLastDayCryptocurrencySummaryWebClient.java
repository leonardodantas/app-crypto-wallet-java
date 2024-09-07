package com.crypto.wallet.infra.integration.webclient;

import com.crypto.wallet.domain.CryptocurrencySummary;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import com.crypto.wallet.infra.integration.json.TickerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
                .bodyToMono(TickerResponse.class)
                .map(summary -> getTicker(summary, digitalCurrencyAcronym));
    }

    private Ticker getTicker(final TickerResponse tickerResponse, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return Ticker.builder()
                .ticker(getCryptocurrencySummary(tickerResponse))
                .digitalCurrencyAcronym(getDigitalCurrencyAcronym(digitalCurrencyAcronym))
                .build();
    }

    private DigitalCurrencyAcronym getDigitalCurrencyAcronym(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return DigitalCurrencyAcronym.builder()
                .name(digitalCurrencyAcronym.getName())
                .description(digitalCurrencyAcronym.getDescription())
                .build();
    }

    private CryptocurrencySummary getCryptocurrencySummary(final TickerResponse tickerResponse) {
        return CryptocurrencySummary.builder()
                .sell(tickerResponse.getSell())
                .high(tickerResponse.getHigh())
                .buy(tickerResponse.getBuy())
                .low(tickerResponse.getLow())
                .vol(tickerResponse.getVol())
                .last(tickerResponse.getLast())
                .open(tickerResponse.getOpen())
                .date(LocalDateTime.ofInstant(Instant.ofEpochSecond(tickerResponse.getDate()), ZoneId.of("America/Sao_Paulo")))
                .build();
    }
}
