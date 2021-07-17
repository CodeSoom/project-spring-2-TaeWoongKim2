package com.codesoom.project.web.controller.advisor;

import com.codesoom.project.common.error.AccountNotFoundException;
import com.codesoom.project.web.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 계좌에 관한 예외 처리 핸들러
 */
@ResponseBody
@ControllerAdvice
public class AccountErrorAdvice {
    /**
     * AccountNotFoundException 예외 발생시, NOT_FOUND 응답코드를 반환합니다.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public ErrorResponse handleAccountNotFound() {
        return new ErrorResponse("Account not found");
    }
}
