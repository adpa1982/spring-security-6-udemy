package com.debugenadoideas.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description ApiKeyFilter
 * @date
 */
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY = "my_Key";
    private static final String API_KEY_HEADER = "api_key";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            final var apiKeyOpt = Optional.of(request.getHeader(API_KEY_HEADER));
            final var apiKey = apiKeyOpt.orElseThrow(() -> new BadCredentialsException("No header api key"));

            if (!apiKey.equals(API_KEY)) {
                throw new BadCredentialsException("Invalid api key");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid api key");
        }

        filterChain.doFilter(request, response);

    }
}
