package com.debugenadoideas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description SecurityConfig
 * @date
 */
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    @Autowired
    SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter) throws Exception {

        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.authorizeHttpRequests(auth ->
                auth
                        /*.requestMatchers("/loans/**").hasAuthority("VIEW_LOANS")
                        .requestMatchers("/balance/**").hasAuthority("VIEW_BALANCE")
                        .requestMatchers("/cards/**").hasAuthority("VIEW_CARDS")*/
                        //.requestMatchers("/accounts/**").hasAnyAuthority("VIEW_ACCOUNT", "VIEW_CARDS")
                        .requestMatchers("/loans/**", "/balance/**").hasRole("USER")
                        .requestMatchers("/accounts/**", "/cards/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        http.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);

        http.cors(cors -> corsConfigurationSource());

        http.csrf(csrf -> csrf
                        .csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/welcome", "/about_us", "/authenticate/")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }

    /*@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();

        // Registrar solo los origins(urls) permitidos
        //config.setAllowedOrigins(List.of("http://localhost:4200", "http://my-app.com"));

        // Permite cualquier origin
        config.setAllowedOrigins(List.of("*"));

        // Permite la operacion de solo los metodos permitidos
        //config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

        // Permite la operaciond e todos los metodos
        config.setAllowedMethods(List.of("*"));

        config.setAllowedHeaders(List.of("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
