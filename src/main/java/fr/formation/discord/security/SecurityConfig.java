package fr.formation.discord.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorization -> {
            authorization
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/connect").permitAll()
                    .requestMatchers("/signup-post").permitAll()
                    .requestMatchers("/perform-login").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/**").authenticated();
        });

        http
                .formLogin(form -> form
                        .loginPage("/connect")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/chathome", true)
                        .failureUrl("/login?erreur")
                );

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}