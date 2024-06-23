package com.crypto.wallet.infra.database.entities;

import com.crypto.wallet.domain.ICryptocurrencyWallet;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class WalletEntity {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "digital_currency_acronym_id", referencedColumnName = "id")
    private DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity;
    private double quantity;

    private WalletEntity(DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity, ICryptocurrencyWallet cryptoWallet) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronymEntity = digitalCurrencyAcronymEntity;
        this.quantity = cryptoWallet.getQuantity();
    }

    public static WalletEntity of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity) {
        return new WalletEntity(digitalCurrencyAcronymEntity, cryptoWallet);
    }

    public void overrideWallet(WalletEntity wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
