package com.codesoom.project.web.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBookResultData {
    private Long id;

    private Integer amount;

    private String description;
}
