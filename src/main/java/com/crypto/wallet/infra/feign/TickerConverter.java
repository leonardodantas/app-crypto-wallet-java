package com.crypto.wallet.infra.feign;

import com.crypto.wallet.domain.CryptocurrencySummary;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import com.crypto.wallet.infra.feign.json.TickerResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TickerConverter {

    public Converter<TickerResponse, Ticker> toDomain(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return (response) -> {
            final var cryptocurrencySummary = CryptocurrencySummary.builder()
                    .buy(response.getBuy())
                    .date(LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getDate()), ZoneId.of("UTC")))
                    .high(response.getHigh())
                    .last(response.getLast())
                    .low(response.getLow())
                    .open(response.getOpen())
                    .sell(response.getSell())
                    .vol(response.getVol())
                    .build();

            return Ticker.builder()
                    .ticker(cryptocurrencySummary)
                    .digitalCurrencyAcronym(digitalCurrencyAcronym)
                    .build();
        };
    }
}
