package com.crypto.wallet.infra.feign.converters;

import com.crypto.wallet.domain.DerivationHistoryPerformed;
import com.crypto.wallet.infra.feign.json.DerivationHistoryPerformedResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class DerivationHistoryPerformedConverter implements Converter<DerivationHistoryPerformedResponse, DerivationHistoryPerformed> {

    @Override
    public DerivationHistoryPerformed convert(final DerivationHistoryPerformedResponse response) {
        return DerivationHistoryPerformed.builder()
                .tid(response.tid())
                .price(response.price())
                .type(response.type())
                .date(LocalDateTime.ofInstant(Instant.ofEpochSecond(response.date()), ZoneId.of("UTF")))
                .amount(response.amount())
                .build();
    }
}
