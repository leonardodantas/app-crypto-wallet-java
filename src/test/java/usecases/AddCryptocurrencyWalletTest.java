package usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.usecases.ISaveWallet;
import com.crypto.wallet.app.usecases.impl.AddCryptocurrencyWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddCryptocurrencyWalletTest {

    @InjectMocks
    private AddCryptocurrencyWallet cryptocurrencyWallet;
    @Mock
    private IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    @Mock
    private ISaveWallet saveWallet;

    @Test
    @DisplayName("Deve lançar exceção quando cripto moeda não existir")
    void shouldThrownCryptocurrencyNotFoundException() {
        final var request = GetMockJson.execute("requests/cryptocurrency-wallet-invalid", CryptocurrencyWalletRequest.class);

        when(digitalCurrencyAcronymRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(CryptocurrencyNotFoundException.class, () -> cryptocurrencyWallet.addCryptocurrency(request));
    }

    @Test
    @DisplayName("Deve salvar cripto com sucesso em sua carteira")
    void shouldSaveCryptocurrencyInWallet() {
        final var request = GetMockJson.execute("requests/cryptocurrency-wallet-valid", CryptocurrencyWalletRequest.class);
        final var digitalCurrencyAcronym = GetMockJson.execute("domains/digital-currency-acronym", DigitalCurrencyAcronym.class);
        final var wallet = GetMockJson.execute("domains/wallet", Wallet.class);

        when(digitalCurrencyAcronymRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(digitalCurrencyAcronym));

        when(saveWallet.save(request, digitalCurrencyAcronym))
                .thenReturn(wallet);

        final var result = cryptocurrencyWallet.addCryptocurrency(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(wallet.getId(), result.getId());
        Assertions.assertEquals(wallet.getQuantity(), result.getQuantity());
    }
}
