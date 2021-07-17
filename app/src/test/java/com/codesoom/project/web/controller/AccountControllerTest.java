package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.AccountService;
import com.codesoom.project.core.domain.Account;
import com.codesoom.project.helper.UTF8EncodingFilter;
import com.codesoom.project.web.dto.account.AccountCreationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.codesoom.project.helper.ApiDocumentUtils.defaultApiDocumentForm;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureRestDocs
@UTF8EncodingFilter
@DisplayName("AccountController 테스트")
class AccountControllerTest {
    private static final Long ACCOUNT_ID = 1L;
    private static final String ACCOUNT_NAME = "개인계좌";

    private static final String ACCOUNT_CREATION_DTO = "{\"name\":\"%s\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        given(accountService.createAccount(any(AccountCreationData.class)))
                .will(invocation -> {
                    AccountCreationData creationData = invocation.getArgument(0);
                    return Account.builder()
                            .id(ACCOUNT_ID)
                            .name(creationData.getName())
                            .build();
                });
    }

    @Test
    @DisplayName("올바른 요청인 경우 신규 계좌 생성 테스트")
    void create() throws Exception {
        mockMvc.perform(
                post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format(ACCOUNT_CREATION_DTO, ACCOUNT_NAME))
        )
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\":" + ACCOUNT_ID + "")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"" + ACCOUNT_NAME + "\"")
                ))
                .andDo(
                        defaultApiDocumentForm("create-account")
                );
    }

}
