package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.repositories.ITickerRepository;
import com.crypto.wallet.domain.CryptocurrencySummary;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import com.crypto.wallet.infra.database.mongodb.jpa.TickerSpringData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TickerRepository implements ITickerRepository {

    private final TickerSpringData tickerSpringData;

    @Override
    public List<Ticker> findAll() {
        return tickerSpringData.findAll()
                .stream().map(document -> {
                    final var digitalCurrencyAcronym = DigitalCurrencyAcronym.builder()
                            .name(document.getDigitalCurrencyAcronym().getName())
                            .description(document.getDigitalCurrencyAcronym().getDescription())
                            .build();

                    final var cryptocurrencySummary = CryptocurrencySummary.builder()
                            .sell(document.getCryptocurrencySummary().getSell())
                            .high(document.getCryptocurrencySummary().getHigh())
                            .buy(document.getCryptocurrencySummary().getBuy())
                            .low(document.getCryptocurrencySummary().getLow())
                            .vol(document.getCryptocurrencySummary().getVol())
                            .last(document.getCryptocurrencySummary().getLast())
                            .open(document.getCryptocurrencySummary().getOpen())
                            .date(document.getCryptocurrencySummary().getDate())
                            .build();

                    return Ticker.builder()
                            .ticker(cryptocurrencySummary)
                            .digitalCurrencyAcronym(digitalCurrencyAcronym)
                            .build();
                }).toList();
    }
}
