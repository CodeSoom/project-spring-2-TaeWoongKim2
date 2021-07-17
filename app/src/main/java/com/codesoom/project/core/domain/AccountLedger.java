package com.codesoom.project.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 계좌내역 도메인
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(nullable = false)
    private Integer amount = 0;

    @Builder.Default
    private String description = "";

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
