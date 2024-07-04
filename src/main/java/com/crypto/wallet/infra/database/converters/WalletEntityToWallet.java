package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.Wallet;
import com.crypto.wallet.infra.database.entities.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletEntityToWallet implements Converter<WalletEntity, Wallet> {

    private final DigitalCurrencyAcronymEntityToDigitalCurrencyAcronym digitalCurrencyAcronymConverter;

    @Override
    public Wallet convert(final WalletEntity entity) {
        return Wallet.builder()
                .id(entity.getId())
                .digitalCurrencyAcronym(digitalCurrencyAcronymConverter.convert(entity.getDigitalCurrencyAcronym()))
                .quantity(entity.getQuantity())
                .build();
    }
}
