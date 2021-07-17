package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.AccountLedger;
import com.codesoom.project.core.domain.AccountLedgerRepository;
import com.codesoom.project.web.dto.account.AccountLedgerItemRegistrationData;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 계좌내역에 관한 유즈케이스를 담당합니다.
 */
@Service
@Transactional
public class AccountBookService {
    private final Mapper mapper;
    private final AccountLedgerRepository accountLedgerRepository;

    public AccountBookService(Mapper mapper, AccountLedgerRepository accountLedgerRepository) {
        this.mapper = mapper;
        this.accountLedgerRepository = accountLedgerRepository;
    }

    /**
     * 신규 계좌내역를 추가합니다.
     *
     * @param registrationData 계좌내역를 생성 위한 데이터
     * @return 등록된 계좌내역
     */
    public AccountLedger addAccountBookItem(AccountLedgerItemRegistrationData registrationData) {
        AccountLedger accountLedgerItem = accountLedgerRepository.save(
            mapper.map(registrationData, AccountLedger.class));

        return accountLedgerItem;
    }
}
