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
                .tid(response.getTid())
                .price(response.getPrice())
                .type(response.getType())
                .date(LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getDate()), ZoneId.of("UTF")))
                .amount(response.getAmount())
                .build();
    }
}
