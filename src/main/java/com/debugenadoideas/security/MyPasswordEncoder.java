package com.debugenadoideas.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description MyPasswordEncoder
 * @date
 */
//@Component
public class MyPasswordEncoder /*implements PasswordEncoder*/ {

    //@Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword.toString().hashCode());
    }

    //@Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        var passwordAsString = String.valueOf(rawPassword.toString().hashCode());
        return encodedPassword.equals(passwordAsString);
    }
}
