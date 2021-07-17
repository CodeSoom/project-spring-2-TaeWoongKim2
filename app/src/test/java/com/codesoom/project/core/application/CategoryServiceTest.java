package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.Category;
import com.codesoom.project.core.domain.CategoryRepository;
import com.codesoom.project.web.dto.category.CategoryRegistrationData;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("CategoryService 테스트")
class CategoryServiceTest {
    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "수입";

    private CategoryService categoryService;

    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        categoryService = new CategoryService(mapper, categoryRepository);

        given(categoryRepository.save(any(Category.class))).will(invocation -> {
            Category source = invocation.getArgument(0);
            return Category.builder()
                    .id(CATEGORY_ID)
                    .name(source.getName())
                    .build();
        });
    }

    @Test
    @DisplayName("신규 계좌내역 카테고리 생성 테스트")
    void createUser() {
        CategoryRegistrationData registrationData = CategoryRegistrationData.builder()
                .name(CATEGORY_NAME)
                .build();

        Category category = categoryService.createCategory(registrationData);

        verify(categoryRepository).save(any(Category.class));

        assertThat(category.getId()).isEqualTo(CATEGORY_ID);
        assertThat(category.getName()).isEqualTo(CATEGORY_NAME);
    }
}
