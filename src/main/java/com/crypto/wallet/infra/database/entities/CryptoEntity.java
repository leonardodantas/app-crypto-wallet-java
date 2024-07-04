package com.crypto.wallet.infra.database.entities;

import com.crypto.wallet.domain.Crypto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "crypto")
public class CryptoEntity {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    private CryptoEntity(final Crypto crypto) {
        this.id = UUID.randomUUID().toString();
        this.typeOperation = TypeOperation.valueOf(crypto.getTypeOperation().getType());
    }

    public static CryptoEntity from(final Crypto crypto) {
        return new CryptoEntity(crypto);
    }
}
