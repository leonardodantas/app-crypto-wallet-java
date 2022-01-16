package com.crypto.wallet.app.utils.simpleregression;

import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DataForCalculation implements Comparable<DataForCalculation>{

    private final LocalDateTime x;
    private final double y;

    private DataForCalculation(DerivationHistoryPerformedResponse derivationHistoryPerformedResponse) {
        this.x = derivationHistoryPerformedResponse.getDate();
        this.y = derivationHistoryPerformedResponse.getPrice().doubleValue();
    }

    public static DataForCalculation from(DerivationHistoryPerformedResponse derivationHistoryPerformedResponse) {
        return new DataForCalculation(derivationHistoryPerformedResponse);
    }

    @Override
    public int compareTo(DataForCalculation o) {
        if(x.isBefore(o.x)) {
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
