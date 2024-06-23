package usecases;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.app.usecases.impl.SaveWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveWalletTest {

    @InjectMocks
    private SaveWallet saveWallet;
    @Mock
    private ISalesHistoryRepository salesHistoryRepository;
    @Mock
    private IWalletRepository walletRepository;
    @Captor
    private ArgumentCaptor<Wallet> argumentCaptorWallet;

    @Test
    @DisplayName("Deve atualizar cripto na carteira")
    void shouldUpdateCryptoInWallet() {
        final var cryptocurrencyWallet = GetMockJson.execute("requests/cryptocurrency-wallet-valid", CryptocurrencyWalletRequest.class);
        final var digitalCurrencyAcronym = GetMockJson.execute("domains/digital-currency-acronym", DigitalCurrencyAcronym.class);
        final var walletToSave = GetMockJson.execute("domains/wallet-1", Wallet.class);
        final var walletSave = GetMockJson.execute("domains/wallet", Wallet.class);

        when(walletRepository
                .findByDigitalCurrencyAcronym(any())).thenReturn(Optional.of(walletSave));

        when(walletRepository
                .save(any())).thenReturn(walletToSave);

        final var result = saveWallet.save(cryptocurrencyWallet, digitalCurrencyAcronym);

        assertNotNull(result);
        assertEquals(20, result.getQuantity());

        verify(salesHistoryRepository).save(any());
        verify(walletRepository).save(argumentCaptorWallet.capture());

        final var walletSaveExpected = argumentCaptorWallet.getValue();
        assertEquals(20, walletSaveExpected.getQuantity());
    }

    @Test
    @DisplayName("Deve salvar cripto na carteira quando não existir nenhum registro na base de dados")
    void shouldSaveWalletWhenNotFoundCriptoInDataBase() {
        final var cryptocurrencyWallet = GetMockJson.execute("requests/cryptocurrency-wallet-valid", CryptocurrencyWalletRequest.class);
        final var digitalCurrencyAcronym = GetMockJson.execute("domains/digital-currency-acronym", DigitalCurrencyAcronym.class);
        final var walletToSave = GetMockJson.execute("domains/wallet", Wallet.class);

        when(walletRepository
                .findByDigitalCurrencyAcronym(any())).thenReturn(Optional.empty());

        when(walletRepository
                .save(any())).thenReturn(walletToSave);

        final var result = saveWallet.save(cryptocurrencyWallet, digitalCurrencyAcronym);

        assertNotNull(result);
        assertEquals(10, result.getQuantity());

        verify(salesHistoryRepository).save(any());
        verify(walletRepository).save(argumentCaptorWallet.capture());

        final var walletSaveExpected = argumentCaptorWallet.getValue();
        assertEquals(10, walletSaveExpected.getQuantity());
    }
}
