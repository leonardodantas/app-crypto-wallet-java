package com.crypto.wallet.infra.libs;

import com.crypto.wallet.app.utils.simpleregression.ISimpleRegression;
import com.crypto.wallet.domain.DataForCalculation;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Component
public class SimpleRegressionMath3 implements ISimpleRegression {

    @Override
    public BigDecimal calculateSimpleRegression(final List<DataForCalculation> dataForCalculationsBuy) {
        Collections.sort(dataForCalculationsBuy);

        final var simpleRegression = new SimpleRegression(true);

        dataForCalculationsBuy
                .forEach(data -> simpleRegression.addData(data.getX(), data.getY()));

        return BigDecimal.valueOf(simpleRegression.predict(Instant.now().getEpochSecond()));
    }
}
