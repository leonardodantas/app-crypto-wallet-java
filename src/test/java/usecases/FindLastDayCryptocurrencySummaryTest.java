package usecases;

import com.crypto.wallet.app.repositories.ITickerRepository;
import com.crypto.wallet.app.usecases.FindLastDayCryptocurrencySummary;
import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;
import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import mocks.GetMockJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindLastDayCryptocurrencySummaryTest {

    @InjectMocks
    private FindLastDayCryptocurrencySummary findLastDayCryptocurrencySummary;
    @Mock
    private ITickerRepository tickerRepository;

    @Test
    void shouldGetAllTicker() {
        final var ticker = GetMockJson.execute("responses/ticket", TickerDocument.class);

        when(tickerRepository.findAll())
                .thenReturn(List.of(ticker));

        final var response = findLastDayCryptocurrencySummary.getAllTicker();
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void shouldGetEmptyList() {

        final var response = findLastDayCryptocurrencySummary.getAllTicker();
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(tickerRepository, times(1)).findAll();
    }
}