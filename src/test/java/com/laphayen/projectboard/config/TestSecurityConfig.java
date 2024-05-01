package com.laphayen.projectboard.config;

import com.laphayen.projectboard.domain.UserAccount;
import com.laphayen.projectboard.repository.UserAccountRepository;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "laphayenTest",
                "qwer1234",
                "laphayentest@email.com",
                "laphayen",
                "test memo"
        )));
    }

}
