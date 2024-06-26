package usecases;

import com.crypto.wallet.app.models.responses.TickerResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindLastDayCryptocurrencySummaryRest;
import com.crypto.wallet.app.usecases.FindLastDayCryptocurrencySummary;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindLastDayCryptocurrencySummaryTest {

    @InjectMocks
    private FindLastDayCryptocurrencySummary findLastDayCryptocurrencySummary;
    @Mock
    private IFindLastDayCryptocurrencySummaryRest lastDayCoinSummary;
    @Mock
    private IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;

    @Test
    void shouldGetAllTicker() {
        final var digitalCurrencyAcronym = GetMockJson.execute("domains/digital-currency-acronym", DigitalCurrencyAcronym.class);
        when(digitalCurrencyAcronymRepository.findAll()).thenReturn(List.of(digitalCurrencyAcronym));

        final var ticket = GetMockJson.execute("domains/ticket", TickerResponse.class);
        when(lastDayCoinSummary.getSummary(any())).thenReturn(ticket);

        final var response = findLastDayCryptocurrencySummary.getAllTicker();
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void shouldGetEmptyList() {
        when(digitalCurrencyAcronymRepository.findAll()).thenReturn(List.of());

        final var response = findLastDayCryptocurrencySummary.getAllTicker();
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(lastDayCoinSummary, never()).getSummary(any());
    }
}
