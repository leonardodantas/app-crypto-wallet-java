package usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import com.crypto.wallet.app.usecases.SaveWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.GetMockJson;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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

    @Test
    void teste() throws JsonProcessingException {

        final var digitalCurrencyAcronym = GetMockJson.execute("domains/digital-currency-acronym", DigitalCurrencyAcronym.class);

        allAttributesNullOrEmpty(digitalCurrencyAcronym);
    }

    @ParameterizedTest
    @MethodSource("getValores")
    void testesss(final Produto produto, final boolean isNull) throws JsonProcessingException {
        final var result = extracted(produto);

        if(isNull) {
            Assertions.assertTrue(result);
        } else {
            Assertions.assertFalse(result);
        }
    }

    public static Stream<Arguments> getValores() {
        return Stream.of(
                Arguments.of(criarProduto("", "", BigDecimal.TEN), false),
                Arguments.of(criarProduto("100", "Teste", BigDecimal.ZERO), false),
                Arguments.of(criarProduto("", " ", null), true),
                Arguments.of(criarProduto(" ", "", null), true),
                Arguments.of(criarProduto("1", "", null), false),
                Arguments.of(criarProduto("", "10", null), false),
                Arguments.of(criarProduto(null, null, null), true),
                Arguments.of(criarProduto(" ", " ", null), true),
                Arguments.of(criarProduto("", "10", null), false),
                Arguments.of(criarProduto("        ", "10", null), false),
                Arguments.of(criarProduto(" ", " ", null), true),
                Arguments.of(criarProduto("", "", null), true),
                Arguments.of(criarProduto("", "10", null), false)
        );
    }

    private static Produto criarProduto(final String id,
                                        final String descricao,
                                        final BigDecimal valor) {

        final Produto produto = new Produto();
        produto.setId(id);
        produto.setDescricao(descricao);
        produto.setValor(valor);

        return produto;
    }

    @Getter
    @Setter
    public static class Produto {
        private String id;
        private String descricao;
        private BigDecimal valor;
    }

    private static boolean extracted(Object object) throws JsonProcessingException {
        final boolean TODOS_ATRIBUTOS_NULL_OU_EMPTY = true;
        final boolean ATRIBUTOS_POPULADOS = false;
        if (Objects.isNull(object)) {
            return TODOS_ATRIBUTOS_NULL_OU_EMPTY;
        }
        final var objectMapper = new ObjectMapper();
        final var rootNode = objectMapper.readTree(objectMapper.writeValueAsString(object));
        if (rootNode.isObject()) {
            for (JsonNode childNode : rootNode) {
                if (!childNode.isNull() && !childNode.asText().trim().isEmpty()) {
                    return ATRIBUTOS_POPULADOS;
                }
            }
        }
        return TODOS_ATRIBUTOS_NULL_OU_EMPTY;
    }

    private static boolean allAttributesNullOrEmpty(final Object object) throws JsonProcessingException {
        if (Objects.isNull(object)) {
            return true;
        }
        final var objectMapper = new ObjectMapper().setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);
        final var jsonAsString = objectMapper.writeValueAsString(object);
        final var jsonAsStringReplace = jsonAsString.replaceAll("\\s+", "");
        return jsonAsStringReplace.equalsIgnoreCase("{}");
    }
}
