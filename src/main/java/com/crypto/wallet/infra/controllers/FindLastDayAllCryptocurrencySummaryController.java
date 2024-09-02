package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.usecases.FindLastDayCryptocurrencySummary;
import com.crypto.wallet.infra.controllers.jsons.responses.ErrorDTO;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lastday/cryptocurrency/summary")
@Tag(name = "APP-CRYPTO-WALLET-JAVA", description = "Gerenciamento de criptomoedas")
public class FindLastDayAllCryptocurrencySummaryController {

    private final FindLastDayCryptocurrencySummary findLastDayCryptocurrencySummary;

    @Operation(summary = "Busca resumo das criptomoedas no ultimo dia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resumos encontrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TickerResponse.class)))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TickerResponse> getSummaryCryptocurrency() {
        return findLastDayCryptocurrencySummary.getAllTicker();
    }
}
