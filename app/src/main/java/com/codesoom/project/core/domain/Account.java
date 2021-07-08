package com.codesoom.project.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 계좌 도메인
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private String name = "";
}
