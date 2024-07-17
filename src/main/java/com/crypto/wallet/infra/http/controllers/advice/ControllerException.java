package com.crypto.wallet.infra.http.controllers.advice;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.infra.http.responses.ErrorResponse;
import com.crypto.wallet.infra.http.responses.ErrorsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerException {

    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = CryptocurrencyNotFoundException.class)
    public ErrorResponse handlerCryptocurrencyNotFoundException(final CryptocurrencyNotFoundException error) {
        return ErrorResponse.from(error.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EntitySaveException.class)
    public ErrorResponse handlerEntitySaveException(final EntitySaveException error) {
        return ErrorResponse.from(error.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public List<ErrorsResponse> validationBeanException(final MethodArgumentNotValidException error) {
        final var fields = error.getBindingResult().getFieldErrors();

        return fields.stream()
                .map(field -> ErrorsResponse.of(field, messageSource.getMessage(field, LocaleContextHolder.getLocale())))
                .toList();
    }
}
