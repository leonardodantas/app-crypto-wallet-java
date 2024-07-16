package usecases;

import com.crypto.wallet.app.usecases.FindCryptocurrencyTrend;
import com.crypto.wallet.app.usecases.FindDerivationHistory;
import com.crypto.wallet.app.utils.simpleregression.ISimpleRegression;
import com.crypto.wallet.domain.DerivationHistoryPerformed;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindCryptocurrencyTrendTest {

    @InjectMocks
    private FindCryptocurrencyTrend findCryptocurrencyTrend;
    @Mock
    private FindDerivationHistory getDerivationHistory;
    @Mock
    private ISimpleRegression simpleRegression;

    @Test
    void shouldGetCryptocurrencyTrend() {
        final var name = "BITCOIN";

        final var derivationHistoriesPerformed = GetMockJson.execute("domains/derivation-history-performed-list", new TypeReference<List<DerivationHistoryPerformed>>() {
        });
        when(getDerivationHistory.getByCryptocurrencyName(name))
                .thenReturn(derivationHistoriesPerformed);

        when(simpleRegression.calculeSimpleRegression(any()))
                .thenReturn(BigDecimal.valueOf(500)).thenReturn(BigDecimal.valueOf(1500));

        final var result = findCryptocurrencyTrend.getByCryptocurrencyName(name);
        assertNotNull(result);

        verify(simpleRegression, times(2)).calculeSimpleRegression(any());
    }

}
