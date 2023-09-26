package fr.formation.discord.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static javax.management.Query.and;



@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorization -> {
            authorization.requestMatchers("/signup").permitAll();
            authorization.requestMatchers("/connect").permitAll();
            authorization.requestMatchers("/signup-post").permitAll();
            authorization.requestMatchers("/perform-login").permitAll();
            authorization.requestMatchers("/").permitAll();
            authorization.requestMatchers("/**").authenticated();
        });

        http
                .formLogin(form -> form
                    .loginPage("/connect")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/chathome", true)
                    .failureUrl("/login?erreur")
                );
    }


    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
