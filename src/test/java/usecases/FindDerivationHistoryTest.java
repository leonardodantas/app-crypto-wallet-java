package usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindDerivationHistoryPerformedRest;
import com.crypto.wallet.app.usecases.FindDerivationHistory;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindDerivationHistoryTest {

    @InjectMocks
    private FindDerivationHistory findDerivationHistory;

    @Mock
    private IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    @Mock
    private IFindDerivationHistoryPerformedRest findDerivationHistoryPerformedRest;

    @Test
    void shouldThrowCryptocurrencyNotFoundException() {
        final var name = "BITTCOIN";

        when(digitalCurrencyAcronymRepository.findByName(name))
                .thenReturn(Optional.empty());

        assertThrows(CryptocurrencyNotFoundException.class, () -> findDerivationHistory.getByCryptocurrencyName(name));

        verify(findDerivationHistoryPerformedRest, never()).getDerivationHistoryPerformed(name);
    }

    @Test
    void shouldGetDerivationHistoryPerformed() {
        final var name = "BITCOIN";
        final var digitalCurrencyAcronym = GetMockJson.execute("domains/digital-currency-acronym", DigitalCurrencyAcronym.class);
        final var derivationHistoryPerformed = GetMockJson.execute("domains/derivation-history-performed", DerivationHistoryPerformedResponse.class);

        when(digitalCurrencyAcronymRepository.findByName(name))
                .thenReturn(Optional.of(digitalCurrencyAcronym));

        when(findDerivationHistoryPerformedRest.getDerivationHistoryPerformed(name))
                .thenReturn(List.of(derivationHistoryPerformed));

        final var result = findDerivationHistory.getByCryptocurrencyName(name);
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(findDerivationHistoryPerformedRest).getDerivationHistoryPerformed(name);
    }

}
