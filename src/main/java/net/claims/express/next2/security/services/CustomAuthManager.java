package net.claims.express.next2.security.services;

import net.claims.express.next2.security.model.CustomTokenAuthentication;
import net.claims.express.next2.security.model.UsernamePasswordAuthentication;
import net.claims.express.next2.security.providers.CustomAuthProvider;
import net.claims.express.next2.security.providers.UsernamePwdProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthManager implements AuthenticationManager {


    @Autowired
    private CustomAuthProvider tokenProvider;

    @Autowired
    private UsernamePwdProvider usernamePwdAuthProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getClass().equals(UsernamePasswordAuthentication.class)){
            System.out.println("authentication object type is: UsernamePasswordAuthentication");
            return this.usernamePwdAuthProvider.authenticate(authentication);
        }
        else if(authentication.getClass().equals(CustomTokenAuthentication.class)){
            return this.tokenProvider.authenticate(authentication);
        }
        else {
            throw new BadCredentialsException("Authentication is not supported");
        }
    }
}
