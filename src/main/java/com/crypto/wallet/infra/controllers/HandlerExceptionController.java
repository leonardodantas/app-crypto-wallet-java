package com.crypto.wallet.infra.controllers;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.infra.controllers.jsons.responses.ErrorResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.ErrorsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class HandlerExceptionController {

    private final MessageSource messageSource;

    @ExceptionHandler(value = CryptocurrencyNotFoundException.class)
        public ResponseEntity<?> coinNotFoundException(final CryptocurrencyNotFoundException error) {
        final ErrorResponse errorResponse = ErrorResponse.from(error.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntitySaveException.class)
    public ResponseEntity<?> entitySaveException(final EntitySaveException error) {
        final ErrorResponse errorResponse = ErrorResponse.from(error.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationBeanException(final MethodArgumentNotValidException error) {
        final List<FieldError> fields = error.getBindingResult().getFieldErrors();

        final List<ErrorsResponse> errors = fields.stream()
                .map(field -> ErrorsResponse.of(field, messageSource.getMessage(field, LocaleContextHolder.getLocale())))
                .toList();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
