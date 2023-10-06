package com.eztraining.userservice.security;

import com.eztraining.userservice.security.handlers.AccessDeniedHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig{

    private final AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    public SecurityConfig(AccessDeniedHandlerImpl accessDeniedHandlerImpl) {
        this.accessDeniedHandlerImpl = accessDeniedHandlerImpl;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(c -> {
            c.disable();
        });

        http.cors(c -> {
            c.configurationSource(corsConfigurationSource());
        });

        http.authorizeHttpRequests(request -> {
            request
                    .anyRequest().permitAll();
        });

        http.addFilterBefore(new RoleCheckRequestFilter(), UsernamePasswordAuthenticationFilter.class);

          http.exceptionHandling(c -> {
                c.accessDeniedHandler(accessDeniedHandlerImpl);
            });

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // You should only set trusted site here. e.g. http://localhost:4200 means only this site can access.
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //source.registerCorsConfiguration("/**", configuration);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
