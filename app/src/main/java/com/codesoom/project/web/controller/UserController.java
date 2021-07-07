package com.codesoom.project.web.controller;

import com.codesoom.project.web.dto.UserRegistrationData;
import com.codesoom.project.web.dto.UserResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * 신규 사용자을 등록하고, 등록한 사용자을 반환합니다.
     *
     * @param registrationData 신규 사용자 정보
     * @return 등록한 사용자
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResultData create(@RequestBody @Valid UserRegistrationData registrationData) {
        return null;
    }
}
