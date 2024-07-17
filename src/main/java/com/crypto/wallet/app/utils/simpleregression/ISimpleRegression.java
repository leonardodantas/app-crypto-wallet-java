package com.crypto.wallet.app.utils.simpleregression;

import java.math.BigDecimal;
import java.util.List;

public interface ISimpleRegression {

    BigDecimal calculeSimpleRegression(final List<DataForCalculation> dataForCalculationsBuy);
}
