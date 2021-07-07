package com.codesoom.project.core.infra;

import com.codesoom.project.core.domain.User;
import com.codesoom.project.core.domain.UserRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaUserRepository
        extends UserRepository, CrudRepository<User, Long> {
    User save(User user);
}
