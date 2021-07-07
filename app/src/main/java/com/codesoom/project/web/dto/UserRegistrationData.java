package com.codesoom.project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
public class UserRegistrationData {
    @NotBlank
    private String email;

    @NotBlank
    private String name;
}
