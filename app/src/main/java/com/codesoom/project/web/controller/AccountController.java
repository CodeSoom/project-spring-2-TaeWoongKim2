package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.AccountService;
import com.codesoom.project.core.domain.Account;
import com.codesoom.project.web.dto.AccountCreationData;
import com.codesoom.project.web.dto.AccountResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 계좌에 관련 요청을 처리하고 그에 따른 응답을 담당합니다.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * 식별자에 해당하는 계좌을 반환합니다.
     * @param id 계좌 식별자
     * @return 계좌
     */
    @GetMapping("{id}")
    public AccountResultData account(@PathVariable Long id) {
        return null;
    }

    /**
     * 신규 계좌을 등록하고, 등록한 계좌을 반환합니다.
     *
     * @param creationData 신규 계좌 정보
     * @return 등록한 계좌
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResultData create(@RequestBody @Valid AccountCreationData creationData) {
        Account account = accountService.createAccount(creationData);
        return getAccountResultData(account);
    }

    private AccountResultData getAccountResultData(Account account) {
        if (account == null) {
            return null;
        }

        return AccountResultData.builder()
                .id(account.getId())
                .name(account.getName())
                .build();
    }
}
