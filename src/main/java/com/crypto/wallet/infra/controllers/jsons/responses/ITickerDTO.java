package com.crypto.wallet.infra.controllers.jsons.responses;

import java.math.BigDecimal;

public interface ITickerDTO {

    BigDecimal getHigh();
    BigDecimal getLow();
    BigDecimal getVol();
    BigDecimal getLast();
    BigDecimal getBuy();
    BigDecimal getOpen();
    BigDecimal getSell();
    long getDate();

}
