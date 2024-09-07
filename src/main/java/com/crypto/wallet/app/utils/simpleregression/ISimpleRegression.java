package com.crypto.wallet.app.utils.simpleregression;

import com.crypto.wallet.domain.DataForCalculation;

import java.math.BigDecimal;
import java.util.List;

public interface ISimpleRegression {

    BigDecimal calculeSimpleRegression(List<DataForCalculation> dataForCalculationsBuy);
}
