package com.codesoom.project.core.domain;

/**
 * 저장소와 계좌내역 도메인 간의 유즈케이스를 정의하는 인터페이스입니다.
 */
public interface AccountLedgerRepository {
    AccountLedger save(AccountLedger accountLedger);
}
