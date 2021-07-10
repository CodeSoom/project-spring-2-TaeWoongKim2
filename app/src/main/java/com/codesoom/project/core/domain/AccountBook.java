package com.codesoom.project.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 계좌내역 도메인
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private Integer amount = 0;

    @Builder.Default
    private String description = "";
}
