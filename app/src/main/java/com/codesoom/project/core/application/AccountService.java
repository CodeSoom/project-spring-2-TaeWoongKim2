package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.Account;
import com.codesoom.project.core.domain.AccountRepository;
import com.codesoom.project.web.dto.AccountRegistrationData;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 계좌에 관한 유즈케이스를 담당합니다.
 */
@Service
@Transactional
public class AccountService {
    private final Mapper mapper;
    private final AccountRepository accountRepository;

    public AccountService(Mapper mapper, AccountRepository accountRepository) {
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    /**
     * 신규 계좌를 생성한다.
     *
     * @param accountRegistrationData 계좌를 생성 위한 데이터
     * @return 등록된 계좌
     */
    public Account createAccount(AccountRegistrationData accountRegistrationData) {
        Account account = accountRepository.save(
                mapper.map(accountRegistrationData, Account.class));

        return account;
    }
}
