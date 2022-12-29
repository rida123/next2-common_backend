package net.claims.express.next2.security.services;

import net.claims.express.next2.security.providers.CustomAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthManager implements AuthenticationManager {

    @Autowired
    private CustomAuthProvider provider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(provider.supports(authentication.getClass())) {
            System.out.println("this authentication is supported");
            return provider.authenticate(authentication);
        }
        throw new BadCredentialsException("Manager Level");
    }
}
