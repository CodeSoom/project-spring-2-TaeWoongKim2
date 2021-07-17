package com.codesoom.project.common.error;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Account not found with ID : " + id);
    }
}
