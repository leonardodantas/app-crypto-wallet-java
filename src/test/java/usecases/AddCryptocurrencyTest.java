package usecases;


import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.domain.Cryptocurrency;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import com.crypto.wallet.app.usecases.SaveWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mocks.GetMockJson;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddCryptocurrencyTest {

    @InjectMocks
    private AddCryptocurrencyWallet cryptocurrencyWallet;
    @Mock
    private IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    @Mock
    private SaveWallet saveWallet;

    @Test
    void shouldThrownCryptocurrencyNotFoundException() {
        final var request = GetMockJson.execute("requests/cryptocurrency-wallet-invalid", Cryptocurrency.class);

        when(digitalCurrencyAcronymRepository.findByName(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(CryptocurrencyNotFoundException.class, () -> cryptocurrencyWallet.addCryptocurrency(request));

        verify(saveWallet, never()).save(any(), any());
    }

    @Test
    void shouldSaveCryptocurrencyInWallet() {
        final var cryptocurrencyWalletRequest = GetMockJson.execute("requests/cryptocurrency-wallet-valid", Cryptocurrency.class);

        final var digitalCurrencyAcronym = GetMockJson.execute("entities/digital-currency-acronym", DigitalCurrencyAcronymDocument.class);
        final var wallet = GetMockJson.execute("entities/wallet", WalletDocument.class);

        when(digitalCurrencyAcronymRepository.findByName(anyString()))
                .thenReturn(Optional.of(digitalCurrencyAcronym));

        when(saveWallet.save(any(), any()))
                .thenReturn(wallet);

        final var result = cryptocurrencyWallet.addCryptocurrency(cryptocurrencyWalletRequest);

        assertNotNull(result);
        assertEquals(wallet.getId(), result.getId());
        assertEquals(wallet.getQuantity(), result.getQuantity());

        verify(saveWallet).save(any(), any());
    }

}