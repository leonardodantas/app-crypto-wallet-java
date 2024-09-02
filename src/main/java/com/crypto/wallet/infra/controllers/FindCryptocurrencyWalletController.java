package com.crypto.wallet.infra.controllers;


import com.crypto.wallet.infra.controllers.jsons.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.FindCryptocurrencyWallet;
import com.crypto.wallet.infra.controllers.jsons.responses.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptocurrency")
@Tag(name = "APP-CRYPTO-WALLET-JAVA", description = "Gerenciamento de criptomoedas")
public class FindCryptocurrencyWalletController {

    private final FindCryptocurrencyWallet findCryptocurrencyWallet;


    @Operation(summary = "Buscar criptomoeda na carteira")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criptomoeda encontrada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CryptocurrencyWalletResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cryptocurrency}/wallet")
    public CryptocurrencyWalletResponse getCryptocurrencyByName(@PathVariable final String cryptocurrency) {
        return findCryptocurrencyWallet.getByName(cryptocurrency);
    }


    @Operation(summary = "Buscar criptomoeda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criptomoedas retornadas com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CryptocurrencyWalletResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})})
    @GetMapping
    public ResponseEntity<?> getAllCryptocurrency() {
        final List<CryptocurrencyWalletResponse> response = findCryptocurrencyWallet.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
