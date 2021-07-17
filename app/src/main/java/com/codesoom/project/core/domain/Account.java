package com.codesoom.project.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 계좌 도메인
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private String name = "";

    @OneToMany(mappedBy = "account")
    private List<AccountLedger> ledgers = new ArrayList<AccountLedger>();
}
