package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.AccountBookService;
import com.codesoom.project.core.domain.AccountBook;
import com.codesoom.project.helper.UTF8EncodingFilter;
import com.codesoom.project.web.dto.account.AccountBookItemRegistrationData;
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

@WebMvcTest(AccountBookController.class)
@AutoConfigureRestDocs
@UTF8EncodingFilter
@DisplayName("AccountBookController 테스트")
class AccountBookControllerTest {
    private static final Long ID = 1L;
    private static final Integer AMOUNT = 3000000;
    private static final String DESCRIPTION = "월급";

    private static final String ACCOUNTBOOK_ADD_DTO =
            "{\"amount\":\"%s\", \"description\":\"%s\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountBookService accountBookService;

    @BeforeEach
    void setUp() {
        given(accountBookService.addAccountBookItem(
                any(AccountBookItemRegistrationData.class))
        )
                .will(invocation -> {
                    AccountBookItemRegistrationData registrationData
                            = invocation.getArgument(0);
                    return AccountBook.builder()
                            .id(ID)
                            .amount(registrationData.getAmount())
                            .description(registrationData.getDescription())
                            .build();
                });
    }

    @Test
    @DisplayName("올바른 요청인 경우 신규 계좌 생성 테스트")
    void add() throws Exception {
        mockMvc.perform(
                post("/accountBooks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(String.format(ACCOUNTBOOK_ADD_DTO, AMOUNT, DESCRIPTION))
        )
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\":" + ID + "")
                ))
                .andExpect(content().string(
                        containsString("\"amount\":" + AMOUNT + "")
                ))
                .andExpect(content().string(
                        containsString("\"description\":\"" + DESCRIPTION + "\"")
                ))
                .andDo(
                        defaultApiDocumentForm("create-accountbook-item")
                );
    }
}