package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.infra.controllers.advice.response.ErrorDTO;
import com.crypto.wallet.app.models.responses.TickerResponse;
import com.crypto.wallet.app.usecases.IFindLastDayCryptocurrencySummary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
@Api(tags = "Summary Cryptocurrency")
@RequestMapping("/lastday/cryptocurrency/summary")
public class FindLastDayAllCryptocurrencySummaryController {

    private final IFindLastDayCryptocurrencySummary findLastDayCryptocurrencySummary;

    public FindLastDayAllCryptocurrencySummaryController(IFindLastDayCryptocurrencySummary findLastDayCryptocurrencySummary) {
        this.findLastDayCryptocurrencySummary = findLastDayCryptocurrencySummary;
    }

    @GetMapping
    @ApiOperation(tags = "Summary Cryptocurrency", value = "24 hour summary of all available cryptocurrencies")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = TickerResponse.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Error", response = ErrorDTO.class)
    })
    public ResponseEntity<?> getSummaryCryptocurrencyripto(){
        List<TickerResponse> response = findLastDayCryptocurrencySummary.getAllTicker();
        return ResponseEntity.ok(response);
    }
}
