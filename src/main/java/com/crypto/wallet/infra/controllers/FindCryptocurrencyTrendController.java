package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.usecases.FindCryptocurrencyTrend;
import com.crypto.wallet.infra.controllers.jsons.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptocurrency")
@Tag(name = "APP-CRYPTO-WALLET-JAVA", description = "Gerenciamento de criptomoedas")
public class FindCryptocurrencyTrendController {

    private final FindCryptocurrencyTrend findCryptocurrencyTrend;

    @Operation(summary = "Busca de tendencia para criptomoeda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tendencias retornadas com sucesso",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CryptocurrencyTrendResponse.class)))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cryptocurrency}/trend")
    public List<CryptocurrencyTrendResponse> getFindCryptocurrencyTrendByName(@PathVariable final String cryptocurrency) {
        return this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
    }
}
