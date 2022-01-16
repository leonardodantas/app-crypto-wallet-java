package com.crypto.wallet.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
