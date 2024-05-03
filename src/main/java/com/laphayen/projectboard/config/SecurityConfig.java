package com.laphayen.projectboard.config;

import com.laphayen.projectboard.dto.UserAccountDto;
import com.laphayen.projectboard.dto.security.BoardPrincipal;
import com.laphayen.projectboard.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers(String.valueOf(PathRequest.toStaticResources().atCommonLocations())).permitAll()
                       .requestMatchers(
                               HttpMethod.GET,
                               "/",
                               "/articles",
                               "/articles/search-hashtag",
                               "/css/**","/scripts/**","/plugin/**","/fonts/**"
                       ).permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin(Customizer.withDefaults())
               .logout(logout -> logout
                       .logoutSuccessUrl("/")
               );
       return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        return username -> userAccountRepository
                .findById(username)
                .map(UserAccountDto::from)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
