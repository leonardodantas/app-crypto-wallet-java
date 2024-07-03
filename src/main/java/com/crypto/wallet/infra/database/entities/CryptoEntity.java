package com.crypto.wallet.infra.database.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "crypto")
public class CryptoEntity {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    private CryptoEntity(TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.typeOperation = operation;
    }

    public static CryptoEntity from(TypeOperation operation) {
        return new CryptoEntity(operation);
    }
}
