package fr.formation.discord.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DemoFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("DEMO-TOKEN");

        if (token != null && token.equals("XXX")) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            
            Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password qui sert a rien", authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        System.out.println("PRINCIPE DU FILTRE : Appelé à chaque request ...");

        // On passe au filtre suivant ...
        filterChain.doFilter(request, response);
    }
    
}
