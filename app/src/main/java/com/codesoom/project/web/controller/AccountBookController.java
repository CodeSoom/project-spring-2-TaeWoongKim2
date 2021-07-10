package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.AccountBookService;
import com.codesoom.project.core.application.AccountService;
import com.codesoom.project.core.domain.Account;
import com.codesoom.project.core.domain.AccountBook;
import com.codesoom.project.web.dto.AccountBookItemRegistrationData;
import com.codesoom.project.web.dto.AccountBookResultData;
import com.codesoom.project.web.dto.AccountCreationData;
import com.codesoom.project.web.dto.AccountResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 계좌내역에 관련 요청을 처리하고 그에 따른 응답을 담당합니다.
 */
@RestController
@RequestMapping("/accountBooks")
public class AccountBookController {
    private final AccountBookService accountBookService;

    public AccountBookController(AccountBookService accountBookService) {
        this.accountBookService = accountBookService;
    }

    /**
     * 신규 계좌내역을 추가하고, 등록한 계좌내역을 반환합니다.
     *
     * @param registrationData 신규 계좌 정보
     * @return 등록한 계좌
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountBookResultData add(@RequestBody @Valid AccountBookItemRegistrationData registrationData) {
        AccountBook accountBook = accountBookService.addAccountBookItem(registrationData);
        return getAccountBookResultData(accountBook);
    }

    private AccountBookResultData getAccountBookResultData(AccountBook accountBook) {
        if (accountBook == null) {
            return null;
        }

        return AccountBookResultData.builder()
                .id(accountBook.getId())
                .amount(accountBook.getAmount())
                .description(accountBook.getDescription())
                .build();
    }
}
