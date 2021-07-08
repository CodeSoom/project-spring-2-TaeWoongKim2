package com.codesoom.project.core.application;

import com.codesoom.project.core.domain.User;
import com.codesoom.project.core.domain.UserRepository;
import com.codesoom.project.web.dto.UserRegistrationData;
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

@DisplayName("UserService 테스트")
class UserServiceTest {
    private static final Long USER_ID = 1L;
    private static final String USER_EMAIL = "tester@example.com";
    private static final String USER_NAME = "테스터";

    private UserService userService;

    private final UserRepository userRepository = mock(UserRepository.class);

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        userService = new UserService(mapper, userRepository);

        given(userRepository.save(any(User.class))).will(invocation -> {
            User source = invocation.getArgument(0);
            return User.builder()
                    .id(USER_ID)
                    .email(source.getEmail())
                    .name(source.getName())
                    .build();
        });
    }
    
    @Test
    @DisplayName("사용자 생성 테스트")
    void createUser() {
        UserRegistrationData registrationData = UserRegistrationData.builder()
                .email(USER_EMAIL)
                .name(USER_NAME)
                .build();

        User user = userService.createUser(registrationData);

        verify(userRepository).save(any(User.class));

        assertThat(user.getId()).isEqualTo(USER_ID);
        assertThat(user.getEmail()).isEqualTo(USER_EMAIL);
        assertThat(user.getName()).isEqualTo(USER_NAME);
    }
}
