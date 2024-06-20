package com.crypto.wallet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
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
