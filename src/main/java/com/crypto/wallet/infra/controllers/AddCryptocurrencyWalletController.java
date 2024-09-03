package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.usecases.AddCryptocurrencyWallet;
import com.crypto.wallet.infra.controllers.jsons.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.domain.CryptocurrencyWallet;
import com.crypto.wallet.infra.controllers.jsons.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
@Tag(name = "APP-CRYPTO-WALLET-JAVA", description = "Gerenciamento de criptomoedas")
public class AddCryptocurrencyWalletController {

    private final AddCryptocurrencyWallet addCryptocurrencyWallet;

    @Operation(summary = "Adicionar criptomoedas na carteira")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criptomoeda adicionada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CryptocurrencyWallet.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CryptocurrencyWalletResponse addCryptocurrency(@Valid @RequestBody final CryptocurrencyWalletRequest body) {
        final var domain = addCryptocurrencyWallet.addCryptocurrency(body);
        return CryptocurrencyWalletResponse.from(domain);
    }
}
