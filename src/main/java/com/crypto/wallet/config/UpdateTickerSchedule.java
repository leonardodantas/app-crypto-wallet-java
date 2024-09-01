package com.crypto.wallet.config;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindLastDayCryptocurrencySummaryRest;
import com.crypto.wallet.infra.controllers.jsons.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.infra.database.mongodb.documents.ScheduleLogDocument;
import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import com.crypto.wallet.infra.database.mongodb.jpa.IScheduleLogMongoRepository;
import com.crypto.wallet.infra.database.mongodb.jpa.ITickerDocumentMongoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UpdateTickerSchedule {

    private final IFindLastDayCryptocurrencySummaryRest lastDayCoinSummary;
    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final IScheduleLogMongoRepository scheduleLogMongoRepository;
    private final ITickerDocumentMongoRepository tickerDocumentMongoRepository;
    private final IFindLastDayCryptocurrencySummaryRest findLastDayCryptocurrencySummary;

    @PostConstruct
    public void initialize() {
        validateUpdateTickers();
    }

    private void validateUpdateTickers() {
        final var SCHEDULE_NAME = "UPDATE_TICKER_SCHEDULE";
        final var THREE_HOURS_TO_MINUTES = 180;
        scheduleLogMongoRepository.findByName(SCHEDULE_NAME)
                .map(scheduleLogDocument -> {
                    final var now = LocalDateTime.now();

                    final var duration = Duration.between(scheduleLogDocument.getUpdateAt(), now).toMinutes();

                    if (duration > THREE_HOURS_TO_MINUTES) {
                        final var scheduleLogDocumentUpdate = scheduleLogDocument.of(now);
                        scheduleLogMongoRepository.save(scheduleLogDocumentUpdate);
                        updateTicker();
                        return Boolean.TRUE;
                    }

                    return Boolean.FALSE;
                }).orElseGet(() -> {
                    final var scheduleLogDocument = ScheduleLogDocument.from(SCHEDULE_NAME);
                    scheduleLogMongoRepository.save(scheduleLogDocument);
                    updateTicker();
                    return Boolean.TRUE;
                });
    }

    private void updateTicker() {
        tickerDocumentMongoRepository.deleteAll();

        final var tickers = digitalCurrencyAcronymRepository.findAll()
                .stream()
                .map(DigitalCurrencyAcronymResponse::from)
                .map(findLastDayCryptocurrencySummary::getSummary)
                .map(TickerDocument::from)
                .toList();

        tickerDocumentMongoRepository.saveAll(tickers);
    }

    @Scheduled(cron = "0 0 0/3 * * ?")
    public void updateTickerSchedule() {
        validateUpdateTickers();
    }
}
