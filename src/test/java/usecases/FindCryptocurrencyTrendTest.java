package usecases;

import com.crypto.wallet.infra.controllers.jsons.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.usecases.FindCryptocurrencyTrend;
import com.crypto.wallet.app.usecases.FindDerivationHistory;
import com.crypto.wallet.app.utils.simpleregression.ISimpleRegression;
import com.crypto.wallet.infra.libs.SimpleRegressionMath3;
import com.fasterxml.jackson.core.type.TypeReference;
import mocks.GetMockJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @Spy
    private ISimpleRegression simpleRegression = spy(SimpleRegressionMath3.class);

    @Test
    void shouldGetCryptocurrencyTrend() {
        final var name = "BITCOIN";

        final var derivationHistoriesPerformed = GetMockJson.execute("responses/derivation-history-performed-list", new TypeReference<List<DerivationHistoryPerformedResponse>>() {
        });

        when(getDerivationHistory.getByCryptocurrencyName(name))
                .thenReturn(derivationHistoriesPerformed);

        final var result = findCryptocurrencyTrend.getByCryptocurrencyName(name);
        assertNotNull(result);

        verify(simpleRegression, times(2)).calculeSimpleRegression(any());
    }

}