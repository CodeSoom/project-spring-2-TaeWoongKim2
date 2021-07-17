package com.codesoom.project.core.infra;

import com.codesoom.project.core.domain.AccountLedger;
import com.codesoom.project.core.domain.AccountLedgerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 특정 저장소에서 계좌내역에 대한 CRUD 작업을 위한 인터페이스입니다.
 */
public interface JpaAccountLedgerRepository
        extends AccountLedgerRepository, JpaRepository<AccountLedger, Long> {
    AccountLedger save(AccountLedger accountLedger);
}
