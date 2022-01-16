package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.infra.controllers.advice.response.ErrorDTO;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.usecases.IAddCryptocurrencyWallet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@RestController
@Api(tags = "Cryptocurrency Wallet")
@RequestMapping("/add-crypto-currency-wallet")
public class AddCryptocurrencyWalletController {

    private final IAddCryptocurrencyWallet addCryptocurrencyWallet;

    public AddCryptocurrencyWalletController(IAddCryptocurrencyWallet addCryptocurrencyWallet) {
        this.addCryptocurrencyWallet = addCryptocurrencyWallet;
    }

    @PostMapping
    @ApiOperation(tags = "Cryptocurrency Wallet", value = "Add cryptocurrency to wallet")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success", response = CryptocurrencyWalletResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorDTO.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCryptocurrency(@Valid @RequestBody CryptocurrencyWalletRequest body) {
        CryptocurrencyWalletResponse response = addCryptocurrencyWallet.addCryptocurrency(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
