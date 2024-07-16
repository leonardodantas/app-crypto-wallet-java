package com.crypto.wallet.app.utils.simpleregression;

import com.crypto.wallet.domain.DerivationHistoryPerformed;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DataForCalculation implements Comparable<DataForCalculation> {

    private final LocalDateTime x;
    private final double y;

    private DataForCalculation(final DerivationHistoryPerformed derivationHistoryPerformed) {
        this.x = derivationHistoryPerformed.getDate();
        this.y = derivationHistoryPerformed.getPrice().doubleValue();
    }

    public static DataForCalculation from(final DerivationHistoryPerformed derivationHistoryPerformed) {
        return new DataForCalculation(derivationHistoryPerformed);
    }

    @Override
    public int compareTo(DataForCalculation o) {
        if (x.isBefore(o.x)) {
            return -1;
        }
        return 1;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(this.x, ZoneId.systemDefault());
        return zonedDateTime.toInstant().getEpochSecond();
    }
}
