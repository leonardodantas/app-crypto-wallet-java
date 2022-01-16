package com.crypto.wallet.infra.controllers.advice;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.infra.controllers.advice.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(value = CryptocurrencyNotFoundException.class)
        public ResponseEntity<?> coinNotFoundException(CryptocurrencyNotFoundException error) {
        ErrorDTO errorDTO = ErrorDTO.from(error.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntitySaveException.class)
    public ResponseEntity<?> entitySaveException(EntitySaveException error) {
        ErrorDTO errorDTO = ErrorDTO.from(error.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
