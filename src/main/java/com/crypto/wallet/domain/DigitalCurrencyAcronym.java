package com.crypto.wallet.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DigitalCurrencyAcronym {

    private String name;
    private String description;

}
