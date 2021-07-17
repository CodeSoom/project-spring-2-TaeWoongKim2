package com.codesoom.project.core.domain;

import java.util.Optional;

/**
 * 저장소와 계좌 도메인 간의 유즈케이스를 정의하는 인터페이스입니다.
 */
public interface AccountRepository {
    Optional<Account> findById(Long id);

    Account save(Account account);
}
