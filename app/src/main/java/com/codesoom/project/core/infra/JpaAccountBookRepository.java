package com.codesoom.project.core.infra;

import com.codesoom.project.core.domain.Account;
import com.codesoom.project.core.domain.AccountBook;
import com.codesoom.project.core.domain.AccountBookRepository;
import com.codesoom.project.core.domain.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 특정 저장소에서 계좌내역에 대한 CRUD 작업을 위한 인터페이스입니다.
 */
public interface JpaAccountBookRepository
        extends AccountBookRepository, JpaRepository<AccountBook, Long> {
    AccountBook save(AccountBook accountBook);
}
