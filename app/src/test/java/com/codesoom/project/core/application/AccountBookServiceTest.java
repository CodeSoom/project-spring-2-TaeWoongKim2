package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.Account;
import com.codesoom.project.core.domain.AccountBook;
import com.codesoom.project.core.domain.AccountBookRepository;
import com.codesoom.project.core.domain.AccountRepository;
import com.codesoom.project.web.dto.AccountBookItemRegistrationData;
import com.codesoom.project.web.dto.AccountCreationData;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("AccountBookService 테스트")
class AccountBookServiceTest {
    private final Long ID = 1L;
    private final Integer AMOUNT = 3000000;
    private final String DESCIPTION = "월급";

    private AccountBookService accountBookService;

    private final AccountBookRepository accountBookRepository
            = mock(AccountBookRepository.class);

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        accountBookService = new AccountBookService(mapper, accountBookRepository);

        given(accountBookRepository.save(any(AccountBook.class)))
                .will(invocation -> {
                    AccountBook source = invocation.getArgument(0);
                    return AccountBook.builder()
                            .id(ID)
                            .amount(AMOUNT)
                            .description(DESCIPTION)
                            .build();
                });
    }

    @Test
    @DisplayName("올바른 요청시 신규 계좌내역 추가 테스트")
    void addAccountHistory() throws Exception {
        AccountBookItemRegistrationData registrationData =
                AccountBookItemRegistrationData.builder()
                        .amount(AMOUNT)
                        .description(DESCIPTION)
                        .build();

        AccountBook accountBook = accountBookService.addAccountBookItem(registrationData);

        verify(accountBookRepository).save(any(AccountBook.class));

        assertThat(accountBook.getId()).isEqualTo(ID);
        assertThat(accountBook.getAmount()).isEqualTo(AMOUNT);
        assertThat(accountBook.getDescription()).isEqualTo(DESCIPTION);
    }
}