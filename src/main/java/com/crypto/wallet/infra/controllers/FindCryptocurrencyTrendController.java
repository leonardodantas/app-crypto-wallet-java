package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.infra.controllers.advice.response.ErrorDTO;
import com.crypto.wallet.app.usecases.IFindCryptocurrencyTrend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
@Api(tags = "Cryptocurrency Trend")
@RequestMapping("/get-cryptocurrency-trend")
public class FindCryptocurrencyTrendController {

    private final IFindCryptocurrencyTrend findCryptocurrencyTrend;

    public FindCryptocurrencyTrendController(IFindCryptocurrencyTrend findCryptocurrencyTrend) {
        this.findCryptocurrencyTrend = findCryptocurrencyTrend;
    }

    @GetMapping("/{cryptocurrency}")
    @ApiOperation(tags = "Cryptocurrency Trend", value = "Returns the selling and buying trend of the informed currency")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = CryptocurrencyTrendResponse.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Error", response = ErrorDTO.class)
    })
    ResponseEntity<?> getFindCryptocurrencyTrendByName(@PathVariable String cryptocurrency){
        List<CryptocurrencyTrendResponse> response = this.findCryptocurrencyTrend.getByCryptocurrencyName(cryptocurrency);
        return ResponseEntity.ok(response);
    }
}
