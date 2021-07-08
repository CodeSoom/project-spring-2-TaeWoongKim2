package com.codesoom.project.core.infra;

import com.codesoom.project.core.domain.Account;
import com.codesoom.project.core.domain.AccountRepository;
import org.springframework.data.repository.CrudRepository;

public interface JpaAccountRepository
        extends AccountRepository, CrudRepository<Account, Long> {
    Account save(Account user);
}
