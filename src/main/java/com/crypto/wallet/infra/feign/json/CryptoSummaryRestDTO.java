package com.crypto.wallet.infra.feign.json;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CryptoSummaryRestDTO {

    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal vol;
    private BigDecimal last;
    private BigDecimal buy;
    private BigDecimal open;
    private BigDecimal sell;
    private long date;

}
