package com.debugenadoideas.security;

import com.debugenadoideas.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description MyAuthenticationProvider
 * @date
 */
@AllArgsConstructor
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final var username = authentication.getName();
        final var pwd = authentication.getCredentials().toString();

        final var customerFromDb = this.customerRepository.findByEmail(username);
        final var customer = customerFromDb.orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        final var customerPwd = customer.getPassword();

        if (!passwordEncoder.matches(pwd, customerPwd)) {
            throw new BadCredentialsException("Invalid credentials");
        }

        final var roles = customer.getRoles();
        final var authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
