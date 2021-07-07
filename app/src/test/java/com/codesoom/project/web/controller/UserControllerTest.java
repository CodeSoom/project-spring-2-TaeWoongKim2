package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.UserService;
import com.codesoom.project.core.domain.User;
import com.codesoom.project.helper.UTF8EncodingFilter;
import com.codesoom.project.web.dto.UserRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.codesoom.project.helper.ApiDocumentUtils.defaultApiDocumentForm;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureRestDocs
@UTF8EncodingFilter
@DisplayName("UserController 테스트")
class UserControllerTest {
    private static final Long USER_ID = 1L;
    private static final String USER_EMAIL = "tester@example.com";
    private static final String USER_NAME = "테스터";

    private static final String CREATE_USER_DTO = "{\"email\":\"%s\",\"name\":\"%s\"}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        given(userService.registerUser(any(UserRegistrationData.class)))
                .will(invocation -> {
                    UserRegistrationData registrationData =
                            invocation.getArgument(0);
                    return User.builder()
                            .id(USER_ID)
                            .email(registrationData.getEmail())
                            .name(registrationData.getName())
                            .build();
                });
    }

    @Test
    @DisplayName("정상적인 요청인 경우 신규 회원을 등록한다")
    void create() throws Exception {
        mockMvc.perform(
                post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(String.format(CREATE_USER_DTO, USER_EMAIL, USER_NAME))
        )
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\":" + USER_ID + "")
                ))
                .andExpect(content().string(
                        containsString("\"email\":\"" + USER_EMAIL + "\"")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"" + USER_NAME + "\"")
                ))
                .andDo(
                        defaultApiDocumentForm("create-user")
                );
    }

}