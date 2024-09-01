package com.crypto.wallet.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@NoArgsConstructor
public class Crypto {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    private Crypto(TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.typeOperation = operation;
    }

    public static Crypto from(TypeOperation operation) {
        return new Crypto(operation);
    }
}
