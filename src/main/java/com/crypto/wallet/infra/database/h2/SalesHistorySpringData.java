package com.crypto.wallet.infra.database.h2;

import com.crypto.wallet.domain.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistorySpringData extends JpaRepository<SalesHistory, String> {
}
