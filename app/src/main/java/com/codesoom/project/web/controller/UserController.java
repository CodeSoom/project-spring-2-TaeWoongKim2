package com.codesoom.project.web.controller;

import com.codesoom.project.core.domain.User;
import com.codesoom.project.web.dto.UserRegistrationData;
import com.codesoom.project.web.dto.UserResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 사용자에 관련 요청을 처리하고 그에 따른 응답을 담당합니다.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    public UserController() {

    }

    /**
     * 신규 사용자을 등록하고, 등록한 사용자을 반환합니다.
     *
     * @param registrationData 신규 사용자 정보
     * @return 등록한 사용자
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResultData create(@RequestBody @Valid UserRegistrationData registrationData) {
        return UserResultData.builder()
                .email(registrationData.getEmail())
                .name(registrationData.getName())
                .build();
    }

    private UserResultData getUserResultData(User user) {
        if (user == null) {
            return null;
        }

        return UserResultData.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
