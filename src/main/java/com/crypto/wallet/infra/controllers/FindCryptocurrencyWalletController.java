package com.crypto.wallet.infra.controllers;


import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.infra.controllers.advice.response.ErrorDTO;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.IFindCryptocurrencyWallet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
@Api(tags = "Find Cryptocurrency Wallet")
@RequestMapping("/cryptocurrency")
public class FindCryptocurrencyWalletController {

    private final IFindCryptocurrencyWallet findCryptocurrencyWallet;

    public FindCryptocurrencyWalletController(IFindCryptocurrencyWallet findCryptocurrencyWallet) {
        this.findCryptocurrencyWallet = findCryptocurrencyWallet;
    }

    @GetMapping("/{cryptocurrency}/wallet")
    @ApiOperation(tags = "Find Cryptocurrency Wallet", value = "Return cryptocurrency if it exists in wallet")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = CryptocurrencyWalletResponse.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Error", response = ErrorDTO.class)
    })
    ResponseEntity<?> getCryptocurrencyByName(@PathVariable String cryptocurrency){
        CryptocurrencyWalletResponse response = findCryptocurrencyWallet.getByName(cryptocurrency);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(tags = "Find Cryptocurrency Wallet", value = "Fetch all cryptocurrency in wallet")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = CryptocurrencyTrendResponse.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Error", response = ErrorDTO.class)
    })
    ResponseEntity<?> getAllCryptocurrency(){
        List<CryptocurrencyWalletResponse> response = findCryptocurrencyWallet.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
