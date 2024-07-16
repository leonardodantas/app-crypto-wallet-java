package usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import com.crypto.wallet.app.usecases.SaveWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddCryptocurrencyWalletTest {

    @InjectMocks
    private AddCryptocurrencyWallet cryptocurrencyWallet;
    @Mock
    private IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    @Mock
    private SaveWallet saveWallet;

    @Test
    @DisplayName("Deve lançar exceção quando cripto moeda não existir")
    void shouldThrownCryptocurrencyNotFoundException() {
        final var name = "BIITCOIN";
        final var quantity = 10D;

        when(digitalCurrencyAcronymRepository.findByName(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(CryptocurrencyNotFoundException.class, () -> cryptocurrencyWallet.addCryptocurrency(name, quantity));

        verify(saveWallet, never()).save(any(), any());
    }

    @Test
    @DisplayName("Deve chamar usecase para salvar cripto com sucesso em sua carteira")
    void shouldSaveCryptocurrencyInWallet() {
        final var name = "BITCOIN";
        final var quantity = 10D;

        final var digitalCurrencyAcronym = GetMockJson.execute("domains/digital-currency-acronym", DigitalCurrencyAcronym.class);
        final var wallet = GetMockJson.execute("domains/wallet", Wallet.class);

        when(digitalCurrencyAcronymRepository.findByName(anyString()))
                .thenReturn(Optional.of(digitalCurrencyAcronym));

        when(saveWallet.save(digitalCurrencyAcronym, quantity))
                .thenReturn(wallet);

        final var result = cryptocurrencyWallet.addCryptocurrency(name, quantity);

        assertNotNull(result);
        assertEquals(wallet.getId(), result.getId());
        assertEquals(wallet.getQuantity(), result.getQuantity());

        verify(saveWallet).save(any(), any());
    }
}
