package com.youcode.misresenas.security;

import com.youcode.misresenas.domains.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig  {
    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthenticationSuccessHandler successHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/users", "/login").permitAll()
                        .requestMatchers("/reviews").hasRole(Role.Admin.name())
                        .requestMatchers("/reviews/add").hasRole(Role.Visitor.name())
                        .requestMatchers("/reviews/delete").hasRole(Role.Admin.name())
                        .requestMatchers("/reviews/update").hasAnyRole(Role.Visitor.name(),Role.Moderator.name(),Role.Admin.name())
                        .requestMatchers("/reviews/repport").hasAnyRole(Role.Moderator.name())
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .formLogin((form) -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/reviews")
                                .failureUrl("/login?error=true")
//                        .successHandler(successHandler)
                                .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }



//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

}