package com.crypto.wallet.app.usecases;

import com.crypto.wallet.domain.DataForCalculation;

import java.math.BigDecimal;
import java.util.List;

public interface ISimpleRegression {

    BigDecimal calculateSimpleRegression(final List<DataForCalculation> dataForCalculations);
}
