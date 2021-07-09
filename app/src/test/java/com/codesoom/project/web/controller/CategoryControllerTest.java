package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.CategoryService;
import com.codesoom.project.core.domain.Category;
import com.codesoom.project.helper.UTF8EncodingFilter;
import com.codesoom.project.web.dto.CategoryRegistrationData;
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

@WebMvcTest(CategoryController.class)
@AutoConfigureRestDocs
@UTF8EncodingFilter
@DisplayName("CategoryController 테스트")
class CategoryControllerTest {
    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "지출";

    private static final String CATEGORY_REGISTRATION_DTO = "{\"name\":\"%s\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        given(categoryService.createCategory(any(CategoryRegistrationData.class)))
                .will(invocation -> {
                    CategoryRegistrationData registrationData =
                            invocation.getArgument(0);
                    return Category.builder()
                            .id(CATEGORY_ID)
                            .name(registrationData.getName())
                            .build();
                });
    }

    @Test
    @DisplayName("정상적인 요청인 경우 신규 카테고리를 등록한다")
    void create() throws Exception {
        mockMvc.perform(
                post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format(CATEGORY_REGISTRATION_DTO, CATEGORY_NAME))
        )
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\":" + CATEGORY_ID + "")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"" + CATEGORY_NAME + "\"")
                ))
                .andDo(
                        defaultApiDocumentForm("create-category")
                );
    }
}
