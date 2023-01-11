package net.claims.express.next2.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

    private final Custom_Authentication_Type authentication_type;

    @Override
    public Object getPrincipal() {
        return super.getPrincipal();
    }

    public UsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
        this.authentication_type = Custom_Authentication_Type.USERNAME_PWD_AUTHENTICATION;
    }

    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.authentication_type = Custom_Authentication_Type.USERNAME_PWD_AUTHENTICATION;
    }

    public Custom_Authentication_Type getAuthentication_type() {
        return authentication_type;
    }
}
