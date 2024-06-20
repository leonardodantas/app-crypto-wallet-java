package com.crypto.wallet.infra.http.controllers.advice;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.infra.http.controllers.advice.response.ErrorDTO;
import com.crypto.wallet.infra.http.controllers.advice.response.ErrorsDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerException {

    private final MessageSource messageSource;

    public ControllerException(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationBeanException(MethodArgumentNotValidException error) {
        List<FieldError> fields = error.getBindingResult().getFieldErrors();

        List<ErrorsDTO> errors = fields.stream()
                .map(field -> ErrorsDTO.of(field, messageSource.getMessage(field, LocaleContextHolder.getLocale())))
                .collect(Collectors.toUnmodifiableList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
