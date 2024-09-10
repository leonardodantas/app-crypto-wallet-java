package com.crypto.wallet.config;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.infra.database.mongodb.documents.ScheduleLogDocument;
import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import com.crypto.wallet.infra.database.mongodb.mongorepositories.IScheduleLogMongoRepository;
import com.crypto.wallet.infra.database.mongodb.mongorepositories.ITickerDocumentMongoRepository;
import com.crypto.wallet.infra.integration.webclient.FindLastDayCryptocurrencySummaryWebClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateTickerSchedule {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final IScheduleLogMongoRepository scheduleLogMongoRepository;
    private final ITickerDocumentMongoRepository tickerDocumentMongoRepository;
    private final FindLastDayCryptocurrencySummaryWebClient findLastDayCryptocurrencySummaryWebClient;

    @PostConstruct
    public void initialize() {
        log.info("EXECUÇÃO DE POSTSCONSTRUCT");
        validateUpdateTickers();
    }

    @Scheduled(cron = "0 0 0/3 * * ?")
    public void updateTickerSchedule() {
        log.info("EXECUÇÃO SCHEDULE TICKERS");
        validateUpdateTickers();
    }

    private void validateUpdateTickers() {
        final var SCHEDULE_NAME = "UPDATE_TICKER_SCHEDULE";
        final var THREE_HOURS_TO_MINUTES = 180;

        scheduleLogMongoRepository.findByName(SCHEDULE_NAME)
                .ifPresentOrElse(scheduleLogDocument -> {
                    final var now = LocalDateTime.now();

                    final var duration = Duration.between(scheduleLogDocument.getUpdateAt(), now).toMinutes();

                    if (duration > THREE_HOURS_TO_MINUTES) {
                        final var scheduleLogDocumentUpdate = scheduleLogDocument.of(now);
                        scheduleLogMongoRepository.save(scheduleLogDocumentUpdate);
                        updateTicker();
                    }
                }, () -> {
                    final var scheduleLogDocument = ScheduleLogDocument.from(SCHEDULE_NAME);
                    scheduleLogMongoRepository.save(scheduleLogDocument);
                    updateTicker();
                });
    }

    private void updateTicker() {
        log.info("ATUALIZANDO TICKERS");
        tickerDocumentMongoRepository.deleteAll();

        final var tickersResponseMono = digitalCurrencyAcronymRepository.findAll()
                .stream()
                .map(findLastDayCryptocurrencySummaryWebClient::getSummary)
                .toList();

        final var tickerResponseFlux = Flux.merge(tickersResponseMono);

        tickerResponseFlux.collectList().subscribe(tickerResponses -> {
            final var tickerDocuments = CollectionUtils.emptyIfNull(tickerResponses)
                    .stream()
                    .map(TickerDocument::from)
                    .toList();

            tickerDocumentMongoRepository.saveAll(tickerDocuments);
            log.info("Finalizando processo assincrono");
        });
    }
}
