package usecases;


import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import com.crypto.wallet.domain.Cryptocurrency;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import mocks.GetMockJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private ISalesHistoryRepository salesHistoryRepository;
    @Mock
    private IWalletRepository walletRepository;
    @Captor
    private ArgumentCaptor<Wallet> argumentCaptorWallet;

    @Test
    void shouldThrownCryptocurrencyNotFoundException() {
        final var request = GetMockJson.execute("requests/cryptocurrency-wallet-invalid", Cryptocurrency.class);

        when(digitalCurrencyAcronymRepository.findByName(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(CryptocurrencyNotFoundException.class, () -> cryptocurrencyWallet.addCryptocurrency(request));

        verify(salesHistoryRepository, never()).save(any());
        verify(walletRepository, never()).findByDigitalCurrencyAcronym(any());
        verify(walletRepository, never()).save(any());
    }

    @Test
    void shouldSaveCryptocurrencyInWallet() {
        final var cryptocurrencyWalletRequest = GetMockJson.execute("requests/cryptocurrency-wallet-valid", Cryptocurrency.class);

        final var digitalCurrencyAcronym = GetMockJson.execute("entities/digital-currency-acronym", DigitalCurrencyAcronym.class);
        final var wallet = GetMockJson.execute("entities/wallet", Wallet.class);

        final var walletToSave = GetMockJson.execute("entities/wallet-1", Wallet.class);
        final var walletSave = GetMockJson.execute("entities/wallet", Wallet.class);

        when(digitalCurrencyAcronymRepository.findByName(anyString()))
                .thenReturn(Optional.of(digitalCurrencyAcronym));

        when(walletRepository
                .findByDigitalCurrencyAcronym(any())).thenReturn(Optional.of(walletSave));

        when(walletRepository
                .save(any())).thenReturn(walletToSave);

        final var result = cryptocurrencyWallet.addCryptocurrency(cryptocurrencyWalletRequest);

        assertNotNull(result);
        assertEquals(wallet.getId(), result.getId());

        verify(salesHistoryRepository, times(1)).save(any());
        verify(walletRepository, times(1)).findByDigitalCurrencyAcronym(any());

        verify(walletRepository).save(argumentCaptorWallet.capture());

        final var walletSaveExpected = argumentCaptorWallet.getValue();
        assertEquals(20, walletSaveExpected.getQuantity());
    }

}