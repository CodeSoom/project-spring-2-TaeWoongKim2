package com.codesoom.project.web.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
public class CategoryRegistrationData {
    @NotBlank
    @Mapping("name")
    private String name;
}
