package com.codesoom.project.web.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBookItemRegistrationData {
    @NotNull
    @Mapping("amount")
    private Integer amount;

    @Mapping("description")
    private String description;
}
