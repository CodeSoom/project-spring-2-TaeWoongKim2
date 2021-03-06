package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.User;
import com.codesoom.project.core.domain.UserRepository;
import com.codesoom.project.web.dto.user.UserRegistrationData;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 사용자에 관한 유즈케이스를 담당합니다.
 */
@Service
@Transactional
public class UserService {
    private final Mapper mapper;
    private final UserRepository userRepository;

    public UserService(Mapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    /**
     * 사용자를 등록한다.
     *
     * @param registrationData 사용자를 등록하기 위한 데이터
     * @return 등록된 사용자
     */
    public User createUser(UserRegistrationData registrationData) {

        User user = userRepository.save(
                mapper.map(registrationData, User.class));

        return user;
    }
}
