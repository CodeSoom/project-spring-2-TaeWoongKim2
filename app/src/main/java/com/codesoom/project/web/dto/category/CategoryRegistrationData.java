package com.codesoom.project.web.dto.category;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRegistrationData {
    @NotBlank
    @Mapping("name")
    private String name;
}
