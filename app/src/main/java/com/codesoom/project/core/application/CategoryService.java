package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.Category;
import com.codesoom.project.core.domain.CategoryRepository;
import com.codesoom.project.web.dto.CategoryRegistrationData;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 계좌내역 카테고리에 관한 유즈케이스를 담당합니다.
 */
@Service
@Transactional
public class CategoryService {
    private final Mapper mapper;
    private final CategoryRepository CategoryRepository;

    public CategoryService(Mapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.CategoryRepository = categoryRepository;
    }

    /**
     * 신규 계좌를 생성한다.
     *
     * @param categoryRegistrationData 계좌를 생성 위한 데이터
     * @return 등록된 계좌
     */
    public Category createCategory(CategoryRegistrationData categoryRegistrationData) {
        Category category = CategoryRepository.save(
                mapper.map(categoryRegistrationData, Category.class));

        return category;
    }
}
