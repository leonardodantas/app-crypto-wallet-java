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
                    final var digitalCurrencyAcronym = DigitalCurrencyAcronym.of(
                            document.getDigitalCurrencyAcronym().getName(),
                            document.getDigitalCurrencyAcronym().getDescription());


                    final var cryptocurrencySummary = CryptocurrencySummary.of(
                            document.getCryptocurrencySummary().getHigh(),
                            document.getCryptocurrencySummary().getLow(),
                            document.getCryptocurrencySummary().getVol(),
                            document.getCryptocurrencySummary().getLast(),
                            document.getCryptocurrencySummary().getBuy(),
                            document.getCryptocurrencySummary().getSell(),
                            document.getCryptocurrencySummary().getOpen(),
                            document.getCryptocurrencySummary().getDate()
                    );

                    return Ticker.of(digitalCurrencyAcronym, cryptocurrencySummary);
                }).toList();
    }
}
