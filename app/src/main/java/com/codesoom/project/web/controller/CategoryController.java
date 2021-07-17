package com.codesoom.project.web.controller;

import com.codesoom.project.core.application.CategoryService;
import com.codesoom.project.core.domain.Category;
import com.codesoom.project.web.dto.category.CategoryRegistrationData;
import com.codesoom.project.web.dto.category.CategoryResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 계좌내역 카테고리에 관련 요청을 처리하고 그에 따른 응답을 담당합니다.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 신규 카테고리를 등록하고, 등록한 카테고리을 반환합니다.
     *
     * @param registrationData 신규 카테고리 정보
     * @return 등록한 카테고리
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResultData create(@RequestBody @Valid CategoryRegistrationData registrationData) {
        Category category = categoryService.createCategory(registrationData);
        return getCategoryResultData(category);
    }

    private CategoryResultData getCategoryResultData(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryResultData.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
