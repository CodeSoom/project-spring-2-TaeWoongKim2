package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.AccountBookRepository;
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
    private final AccountBookRepository accountBookRepository;

    public AccountBookService(Mapper mapper, AccountBookRepository accountBookRepository) {
        this.mapper = mapper;
        this.accountBookRepository = accountBookRepository;
    }
}
    /**
     * 신규 계좌내역를 추가.
     *
     * @param registrationData 계좌내역를 생성 위한 데이터
     * @return 등록된 계좌내역

    public Account addAccountBookItem(AccountBookItemRegistrationData registrationData) {
        Account account = accountBookRepository.save(
                mapper.map(accountCreationData, Account.class));

        return account;
    }*/
