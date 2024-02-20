package org.work.personnelinfo.config.Security;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.work.personnelinfo.admin.Service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserService userService; // Assume this needs to be lazily loaded

    public SecurityConfig(JwtUtil jwtUtil, @Lazy UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtFilterUtil jwtFilterUtil = new JwtFilterUtil(jwtUtil);

        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/user/login").permitAll()
                                .requestMatchers("/personel/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("SUPERUSER", "USER")
                                .anyRequest().authenticated()
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilterUtil, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}