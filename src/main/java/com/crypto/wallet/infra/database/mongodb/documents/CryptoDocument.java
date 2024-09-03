package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.TypeOperation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("crypto")
@NoArgsConstructor
public class CryptoDocument {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    private CryptoDocument(TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.typeOperation = operation;
    }

    public static CryptoDocument from(TypeOperation operation) {
        return new CryptoDocument(operation);
    }
}
