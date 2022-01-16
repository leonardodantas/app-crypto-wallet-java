package com.crypto.wallet.app.models.responses;

import java.math.BigDecimal;

public interface IDerivationHistoryPerformed {
    BigDecimal getAmount();
    long getDate();
    BigDecimal getPrice();
    long getTid();
    String getType();
}
