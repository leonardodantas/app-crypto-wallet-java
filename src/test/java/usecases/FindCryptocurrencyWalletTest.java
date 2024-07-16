package usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.app.usecases.FindCryptocurrencyWallet;
import com.crypto.wallet.domain.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCryptocurrencyWalletTest {

    @InjectMocks
    private FindCryptocurrencyWallet cryptocurrencyWallet;
    @Mock
    private IWalletRepository walletRepository;

    @Test
    void shouldFindCryptoInWalletByName() {
        final var wallet = GetMockJson.execute("domains/wallet", Wallet.class);

        final var crypto = "BITCOIN";

        when(walletRepository.findByCryptocurrencyName(crypto))
                .thenReturn(Optional.of(wallet));

        final var result = cryptocurrencyWallet.getByName(crypto);

        assertNotNull(result);
        assertNotNull(result.getDigitalCurrencyAcronym());
        assertEquals(10, result.getQuantity());
    }

    @Test
    void shouldThrowCryptocurrencyNotFoundException() {
        final var crypto = "BITCOIN";

        when(walletRepository.findByCryptocurrencyName(crypto))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(CryptocurrencyNotFoundException.class, () -> cryptocurrencyWallet.getByName(crypto));
    }


    @Test
    void shouldGetAllCryptoInWallet() {
        final var wallet = GetMockJson.execute("domains/wallet", Wallet.class);

        when(walletRepository.findAll())
                .thenReturn(List.of(wallet));

        final var result = cryptocurrencyWallet.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void shouldGetListEmpty() {
        when(walletRepository.findAll())
                .thenReturn(List.of());

        final var result = cryptocurrencyWallet.getAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
