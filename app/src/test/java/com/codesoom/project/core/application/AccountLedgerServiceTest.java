package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.AccountLedger;
import com.codesoom.project.core.domain.AccountLedgerRepository;
import com.codesoom.project.web.dto.account.AccountLedgerItemRegistrationData;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("AccountBookService 테스트")
class AccountLedgerServiceTest {
    private final Long ID = 1L;
    private final Integer AMOUNT = 3000000;
    private final String DESCIPTION = "월급";

    private AccountBookService accountBookService;

    private final AccountLedgerRepository accountLedgerRepository
            = mock(AccountLedgerRepository.class);

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        accountBookService = new AccountBookService(mapper, accountLedgerRepository);

        given(accountLedgerRepository.save(any(AccountLedger.class)))
                .will(invocation -> {
                    AccountLedger source = invocation.getArgument(0);
                    return AccountLedger.builder()
                            .id(ID)
                            .amount(AMOUNT)
                            .description(DESCIPTION)
                            .build();
                });
    }

    @Test
    @DisplayName("올바른 요청시 신규 계좌내역 추가 테스트")
    void addAccountHistory() throws Exception {
        AccountLedgerItemRegistrationData registrationData =
                AccountLedgerItemRegistrationData.builder()
                        .amount(AMOUNT)
                        .description(DESCIPTION)
                        .build();

        AccountLedger accountLedger = accountBookService.addAccountBookItem(registrationData);

        verify(accountLedgerRepository).save(any(AccountLedger.class));

        assertThat(accountLedger.getId()).isEqualTo(ID);
        assertThat(accountLedger.getAmount()).isEqualTo(AMOUNT);
        assertThat(accountLedger.getDescription()).isEqualTo(DESCIPTION);
    }
}
