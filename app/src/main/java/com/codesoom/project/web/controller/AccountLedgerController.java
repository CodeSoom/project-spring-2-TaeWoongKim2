package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.AccountBookService;
import com.codesoom.project.core.domain.AccountLedger;
import com.codesoom.project.web.dto.account.AccountLedgerItemRegistrationData;
import com.codesoom.project.web.dto.account.AccountLedgerResultData;
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
public class AccountLedgerController {
    private final AccountBookService accountBookService;

    public AccountLedgerController(AccountBookService accountBookService) {
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
    AccountLedgerResultData add(@RequestBody @Valid AccountLedgerItemRegistrationData registrationData) {
        AccountLedger accountLedger = accountBookService.addAccountBookItem(registrationData);
        return getAccountBookResultData(accountLedger);
    }

    private AccountLedgerResultData getAccountBookResultData(AccountLedger accountLedger) {
        if (accountLedger == null) {
            return null;
        }

        return AccountLedgerResultData.builder()
                .id(accountLedger.getId())
                .amount(accountLedger.getAmount())
                .description(accountLedger.getDescription())
                .build();
    }
}
