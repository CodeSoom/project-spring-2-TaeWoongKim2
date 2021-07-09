package com.codesoom.project.core.infra;

import com.codesoom.project.core.domain.Category;
import com.codesoom.project.core.domain.CategoryRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 특정 저장소에서 계좌내역 카테고리에 대한 CRUD 작업을 위한 인터페이스입니다.
 */
public interface JpaCategoryRepository
        extends CategoryRepository, CrudRepository<Category, Long> {
    Category save(Category category);
}
