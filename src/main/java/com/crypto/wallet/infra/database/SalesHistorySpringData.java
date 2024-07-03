package com.crypto.wallet.infra.database;

import com.crypto.wallet.infra.database.entities.SalesHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistorySpringData extends JpaRepository<SalesHistoryEntity, String> {
}
