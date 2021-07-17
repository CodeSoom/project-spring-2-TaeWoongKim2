package com.codesoom.project.core.application;

import com.codesoom.project.common.error.AccountNotFoundException;
import com.codesoom.project.core.domain.Account;
import com.codesoom.project.core.domain.AccountRepository;
import com.codesoom.project.web.dto.account.AccountCreationData;
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
     * ID에 해당하는 계좌를 찾아 반환합니다.
     * @param id 계좌 식별자
     * @return 계좌
     */
    public Account getAccount(Long id) {
        return findAccount(id);
    }

    /**
     * 신규 계좌를 생성한다.
     *
     * @param accountCreationData 계좌를 생성 위한 데이터
     * @return 등록된 계좌
     */
    public Account createAccount(AccountCreationData accountCreationData) {
        Account account = accountRepository.save(
                mapper.map(accountCreationData, Account.class));

        return account;
    }

    /**
     * ID에 해당하는 계좌를 찾습니다.
     * @param id 계좌 식별자
     * @return 계좌
     * @throws AccountNotFoundException ID가 null이거나 해당 계좌가 없을 경우.
     */
    private Account findAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
}
