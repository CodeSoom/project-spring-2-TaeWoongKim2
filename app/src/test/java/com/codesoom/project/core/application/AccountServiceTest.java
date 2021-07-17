package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.Account;
import com.codesoom.project.core.domain.AccountRepository;
import com.codesoom.project.web.dto.account.AccountCreationData;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("AccountService 테스트")
class AccountServiceTest {
    private static final Long ACCOUNT_ID = 1L;
    private static final String ACCOUNT_NAME = "월급통장";

    private AccountService accountService;

    private final AccountRepository accountRepository = mock(AccountRepository.class);

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        accountService = new AccountService(mapper, accountRepository);

        given(accountRepository.save(any(Account.class))).will(invocation -> {
            Account source = invocation.getArgument(0);
            return Account.builder()
                    .id(ACCOUNT_ID)
                    .name(source.getName())
                    .build();
        });

        given(accountRepository.findById(any(Long.class))).will(invocation -> {
            Long findId = invocation.getArgument(0);
            return Optional.of(
                    Account.builder()
                            .id(findId)
                            .name(ACCOUNT_NAME)
                            .build()
            );
        });
    }

    @Test
    @DisplayName("신규 계좌 생성 테스트")
    void createAccount() {
        AccountCreationData registrationData = AccountCreationData.builder()
                .name(ACCOUNT_NAME)
                .build();

        Account account = accountService.createAccount(registrationData);

        verify(accountRepository).save(any(Account.class));

        assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(account.getName()).isEqualTo(ACCOUNT_NAME);
    }

    @Test
    @DisplayName("특정 계좌를 찾고 반환하는 기능 테스트")
    void getAccount() {
        Account account = accountService.getAccount(ACCOUNT_ID);

        verify(accountRepository).findById(any(Long.class));

        assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(account.getName()).isEqualTo(ACCOUNT_NAME);
    }
}
